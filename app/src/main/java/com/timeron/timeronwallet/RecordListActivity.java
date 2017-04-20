package com.timeron.timeronwallet;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.timeron.timeronwallet.adapter.MultiSelectAdapter;
import com.timeron.timeronwallet.resource.WalletDAO;
import com.timeron.timeronwallet.service.entity.WalletRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordListActivity extends AppCompatActivity {

    public static final String ACCOUNT = "account";
    List<Long> checked = new ArrayList<>();
    WalletDAO walletDAO;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        walletDAO = new WalletDAO(this);

        Cursor cursor = walletDAO.getAllRecords();
        ListView listView = (ListView) findViewById(R.id.grid_recordList);
//        createCursorManagedView(cursor, listView);

        final List<Map<String, String>> list = getRecordDataFromCursor(cursor);
//        final SimpleAdapter adapter = new SimpleAdapter(this, list,  R.layout.row_record, new String[]{"_id", WalletDAO.ACCOUNT, WalletDAO.AMOUNT, WalletDAO.TYPE, WalletDAO.DATE, WalletDAO.INCOME}, new int[]{R.id.lr_id, R.id.lr_account, R.id.lr_amount, R.id.lr_type, R.id.lr_date, R.id.lr_income});
        final MultiSelectAdapter adapter = new MultiSelectAdapter(this, list,  R.layout.row_record, new String[]{"_id", WalletDAO.ACCOUNT, WalletDAO.AMOUNT, WalletDAO.TYPE, WalletDAO.DATE, WalletDAO.INCOME}, new int[]{R.id.lr_id, R.id.lr_account, R.id.lr_amount, R.id.lr_type, R.id.lr_date, R.id.lr_income});
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long rowId = new Long(id);
                if(adapter.getSelectedIds().contains(rowId)){
                    adapter.removeSelection(rowId);
                }else {
                    adapter.addSelection(rowId);
                }
                checked = adapter.getSelectedIds();
                adapter.notifyDataSetChanged();
            }
        };
        listView.setOnItemClickListener(itemClickListener);
    }

    private List<Map<String,String>> getRecordDataFromCursor(Cursor cursor) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> row;
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            row = new HashMap<>();
            for(int i = 0; i < cursor.getColumnCount(); i++) {
                String columnName = cursor.getColumnName(i);
                String value = cursor.getString(i);
                if(columnName.equals(WalletDAO.DATE) || columnName.equals(WalletDAO.UPDATE)){
                    row.put(columnName, dateFormat.format(new Date(cursor.getLong(i))));
                }else if(columnName.equals(WalletDAO.INCOME)){
                    if(value.equals("1")) {
                        row.put(columnName, WalletRecord.INCOME_TRUE);
                    }else{
                        row.put(columnName, WalletRecord.INCOME_FALSE);
                    }
                }else {
                    if(value != null) {
                        row.put(columnName, value);
                    }else{
                        row.put(columnName, "");
                    }
                }
            }
            list.add(row);
        }
        return list;
    }

    public void onClickRemove(View view) {
        walletDAO = new WalletDAO(this);
        for(Long record : checked){
            walletDAO.removeRecord(record);
        }

        checked = new ArrayList<>();
        Toast toast = Toast.makeText(this, "Rekordy usunięte!", Toast.LENGTH_SHORT);
        toast.show();

        finish();
        startActivity(getIntent());
    }
}
