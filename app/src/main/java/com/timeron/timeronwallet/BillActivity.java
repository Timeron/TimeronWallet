package com.timeron.timeronwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.timeron.timeronwallet.calculator.match.CalculatorMath;
import com.timeron.timeronwallet.constant.CalculatorButton;
import com.timeron.timeronwallet.constant.Type;
import com.timeron.timeronwallet.exception.WalletValidationException;

import java.math.BigDecimal;
import java.util.Date;

public class BillActivity extends AppCompatActivity {

    private static final int REQUEST_CLICK_AMOUNT = 0 ;
    private static final String CURRENCY = " zł";
    public static final String RESULT_TYPE = "type";
    public static final String RESULT_AMOUNT = "amount";

    private TextView amountTV;
    Spinner spinnerType;

    private static BigDecimal amount;
    Type[] types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        amount = CalculatorMath.getZero();
        this.amountTV = (TextView) findViewById(R.id.wmAmount);
        spinnerType = (Spinner) findViewById(R.id.wmType_spinner);

        types = Type.values();
        ArrayAdapter<Type> typeAdapter = new ArrayAdapter<Type>(this, R.layout.spinner_wallet_type_item, types);
        spinnerType.setAdapter(typeAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode){
            case REQUEST_CLICK_AMOUNT:
                if (resultCode == Activity.RESULT_OK) {
                    this.amount = new BigDecimal(intent.getStringExtra(CalculatorActivity.RESULT_VALUE));
                    if(this.amount == null) {
                        this.amount = CalculatorMath.getZero();
                    }
                    this.amountTV.setText(buildAmount(amount));
                }
                break;
        }
    }

    public void onClickBillSend(View view) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_AMOUNT, amount.toString());
        Type type = (Type) spinnerType.getSelectedItem();
        intent.putExtra(RESULT_TYPE, type.getId()-1);
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    public void onClickAmount(View view) {
        Intent calculatorIntent = new Intent(this, CalculatorActivity.class);
        calculatorIntent.putExtra(CalculatorActivity.START_VALUE, amount.toString());
        startActivityForResult(calculatorIntent, REQUEST_CLICK_AMOUNT);
    }

    private String buildAmount(BigDecimal value) {
        return value.toString()+CURRENCY;
    }

}
