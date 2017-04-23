package com.timeron.timeronwallet;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.timeron.timeronwallet.actitityModule.ApplicationInfoActivity;
import com.timeron.timeronwallet.calculator.match.CalculatorMath;
import com.timeron.timeronwallet.constant.Account;
import com.timeron.timeronwallet.constant.Type;
import com.timeron.timeronwallet.rest.RestService;
import com.timeron.timeronwallet.service.WalletService;
import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.resource.async.SaveRecordAsyncTask;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WalletMainActivity extends AppCompatActivity {

    private static final int REQUEST_CLICK_AMOUNT = 0 ;
    private static final int REQUEST_CLICK_DATE = 1 ;
    private static final int REQUEST_CLICK_BILL = 2;
    private static final String  CURRENCY = " zł";

    Type[] types;
    Account[] accounts;
    WalletRecord record;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    WalletService walletService;

    Spinner spinnerAccount;
    Spinner spinnerType;
    TextView dateTextTV;
    TextView amountTV;
    Switch incomeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_main);
        walletService = new WalletService(this);

        spinnerAccount = (Spinner) findViewById(R.id.wmAccount_spinner);
        spinnerType = (Spinner) findViewById(R.id.wmType_spinner);
        dateTextTV = (TextView) findViewById(R.id.wmDate);
        amountTV = (TextView) findViewById(R.id.wmAmount);
        incomeSwitch = (Switch) findViewById(R.id.wmIncome_switch);

        types = Type.values();
        ArrayAdapter<Type> typeAdapter = new ArrayAdapter<>(this, R.layout.spinner_wallet_type_item, types);
        spinnerType.setAdapter(typeAdapter);

        accounts = Account.values();
        ArrayAdapter<Account> accountAdapter = new ArrayAdapter<>(this, R.layout.spinner_wallet_type_item,accounts);
        spinnerAccount.setAdapter(accountAdapter);

        buildStartRecord();

        dateTextTV.setText(dateFormat.format(this.record.getDate()));
        amountTV.setText(buildAmount(this.record.getAmount()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode){
            case REQUEST_CLICK_AMOUNT:
                if (resultCode == Activity.RESULT_OK) {
                    this.amountTV = (TextView) findViewById(R.id.wmAmount);
                    this.record.setAmount(new BigDecimal(intent.getStringExtra(CalculatorActivity.RESULT_VALUE)));
                    if(this.record.getAmount() == null) {
                        this.record.setAmount(CalculatorMath.getZero());
                    }
                    this.amountTV.setText(buildAmount(this.record.getAmount()));
                }
                break;
            case REQUEST_CLICK_DATE:
                if (resultCode == Activity.RESULT_OK) {
                    TextView dateTextTV = (TextView) findViewById(R.id.wmDate);
                    long dateMs = intent.getLongExtra(CalendarActivity.DATE_PICKER, 0);
                    if(dateMs != 0){
                        this.record.setDate(new Date(dateMs));
                    }else{
                        this.record.setDate(new Date());
                    }

                    dateTextTV.setText(dateFormat.format(this.record.getDate()));
                }
                break;
            case REQUEST_CLICK_BILL:
                if (resultCode == Activity.RESULT_OK) {
                    String billAmount = intent.getStringExtra(BillActivity.RESULT_AMOUNT);
                    int billTypeId = intent.getIntExtra(BillActivity.RESULT_TYPE, 0);
                    Type[] types = Type.values();
                    addBill(billAmount, types[billTypeId]);
                    buildBillList();
                }
                break;
        }
    }


    public void onClickAmount(View view) {
        Log.i("Log", "onClickAmount");
        Intent calculatorIntent = new Intent(this, CalculatorActivity.class);
        calculatorIntent.putExtra(CalculatorActivity.START_VALUE, this.record.getAmount().toString());
        startActivityForResult(calculatorIntent, REQUEST_CLICK_AMOUNT);
    }


    public void onClickDate(View view) {
        Log.i("Log", "onClickDate");
        Intent calendarIntent = new Intent(this, CalendarActivity.class);
        startActivityForResult(calendarIntent, REQUEST_CLICK_DATE);
    }

    public void onClickIncome(View view) {
        this.record.setIncome(incomeSwitch.isChecked());
        Log.i("Log", "income set to "+this.getIntent());
    }

    public void onClickSave(View view) {
        this.record.setAccount(spinnerAccount.getSelectedItem().toString());
        this.record.setType(spinnerType.getSelectedItem().toString());
        if(!this.record.getAmount().equals(CalculatorMath.round(new BigDecimal(0)))) {
            for(WalletRecord r : this.record.getChildRecords()){
                r.setSync(false);
                r.setUpdate(this.record.getUpdate());
                r.setIncome(this.record.getIncome());
                r.setDate(this.record.getDate());
                r.setAccount(this.record.getAccount());
                r.setDescription(this.record.getDescription());
            }
            SaveRecordAsyncTask saveRecordAsyncTask = new SaveRecordAsyncTask(this);
            saveRecordAsyncTask.execute(this.record);

            cleanView();
        }else{
            Toast toast = Toast.makeText(this, R.string.MS_NO_AMOUNT_VALUE, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickClose(View view) {
        cleanView();
    }

    public void onClickAddBill(View view) {
        addBillRow();
    }



    public void onClickRemoveBill(View view) {
        LinearLayout layout = (LinearLayout) view.getParent();
        TextView amount = (TextView) layout.findViewById(R.id.bl_amount);
        TextView type = (TextView) layout.findViewById(R.id.bl_type);
        if(amount != null && type != null) {
            String amountStr = amount.getText().toString();
            String typeStr = type.getText().toString();

            if(record.removeChild(amountStr, typeStr)){
                this.record.setAmount(this.record.getAmount().add(new BigDecimal(amountStr)));
                this.amountTV.setText(this.record.getAmount().toString());
            }
            buildBillList();
        }
    }

    private void addBillRow() {
        Intent billIntent = new Intent(this, BillActivity.class);
        startActivityForResult(billIntent, REQUEST_CLICK_BILL);
    }

    private void buildStartRecord() {
        this.record = new WalletRecord();
        this.record.setAmount(CalculatorMath.getZero());
        this.record.setIncome(false);
        this.record.setType(types[0].toString());
        this.record.setAccount(accounts[0].getName());
        this.record.setDate(new Date());
        this.record.setUpdate(new Date());
        buildBillList();
    }

    private String buildAmount(BigDecimal value) {
        return value.toString()+CURRENCY;
    }

    private void addBill(String amount, Type type){
        WalletRecord childRecord = new WalletRecord();
        childRecord.setAmount(new BigDecimal(amount));
        childRecord.setType(type.getName());
        record.addChildRecord(childRecord);
        this.record.setAmount(this.record.getAmount().subtract(new BigDecimal(amount)));
        this.amountTV.setText(String.format(this.record.getAmount().toString()));

    }

    private void buildBillList() {
        ListView billContainer = (ListView) findViewById(R.id.wmBillListView);
        List<Map<String, String>> list = record.getChildRecordMap();
        ListAdapter adapter = new SimpleAdapter(this, list, R.layout.row_bill, new String[]{"amount", "type"}, new int[] {R.id.bl_amount, R.id.bl_type});
        billContainer.setAdapter(adapter);
    }

    private void cleanView(){
        buildStartRecord();

        this.incomeSwitch.setChecked(this.record.getIncome());
        dateTextTV.setText(dateFormat.format(this.record.getDate()));
        this.spinnerAccount.setSelection(0);
        this.amountTV.setText(this.record.getAmount().toString());
        this.spinnerType.setSelection(0);
    }

    /**
     * Main Menu
     */

    /**
     * Open all records list
     * @param item
     */
    public void OnClickOpenRecords(MenuItem item) {
        Intent intent = new Intent(this, RecordListActivity.class);
        intent.putExtra(RecordListActivity.ACCOUNT, this.record.getAccount());
        startActivity(intent);
    }

    /**
     * Sync records without opening new View
     * @param item
     */
    public void OnClickSync(MenuItem item) {
        walletService.syncRecords();
    }

    public void OnClickAppInfo(MenuItem item){
        Intent intent = new Intent(this, ApplicationInfoActivity.class);
        startActivity(intent);
    }

    public void OnClickAvailability(MenuItem item) {
        checkServerAvailability();
    }

    private void checkServerAvailability() {
        RestService restService = new RestService(this);
        restService.serverAvailability();
    }
}
