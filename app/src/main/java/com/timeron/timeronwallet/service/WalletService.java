package com.timeron.timeronwallet.service;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.timeron.timeronwallet.WalletMainActivity;
import com.timeron.timeronwallet.resource.WalletDAO;
import com.timeron.timeronwallet.rest.RestService;
import com.timeron.timeronwallet.service.dto.WalletRecordDTO;
import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.util.ResultImpl;
import com.timeron.timeronwallet.util.interf.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Timeron on 2017-03-31.
 *
 */

public class WalletService {

    WalletDAO walletDAO;
    RestService restService;
    Context context;



    public WalletService(Context walletMainActivity) {
        this.context = walletMainActivity;
        walletDAO = new WalletDAO(walletMainActivity);
        restService = new RestService(walletMainActivity);
    }

    public void syncRecords() {
        Cursor walletCursor = walletDAO.getAllNotSynchRecords();
        List<WalletRecord> walletRecord = transformToWalletDTO(walletCursor);
        int count = 0;
        for(final WalletRecord record : walletRecord){
            Result result = restService.syncRecord(new WalletRecordDTO(record), new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        boolean success = response.getBoolean("success");
                        if(success){
                            record.setSync(true);
                            walletDAO.updateRecord(record);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            if(!result.isSuccess()){
                Toast toast = Toast.makeText(context, result.getErrors().get(0), Toast.LENGTH_SHORT);
                toast.show();
            }else {
                count++;
            }
        }
        Toast toast = Toast.makeText(context, count+" Rekordów synchronizowanych z "+walletRecord.size(), Toast.LENGTH_LONG);
        toast.show();
    }

    private List<WalletRecord> transformToWalletDTO(Cursor walletCursor) {
        List<WalletRecord> records = new ArrayList<>();
        WalletRecord record;
        walletCursor.moveToFirst();
        for(walletCursor.moveToFirst(); !walletCursor.isAfterLast(); walletCursor.moveToNext()) {
            record = new WalletRecord();
            for(int i = 0; i < walletCursor.getColumnCount(); i++) {
                String columnName = walletCursor.getColumnName(i);
                String value = walletCursor.getString(i);
                switch(columnName){
                    case WalletDAO.ID :
                        record.setId(Integer.parseInt(value));
                        break;
                    case WalletDAO.ACCOUNT :
                        record.setAccount(value);
                        break;
                    case WalletDAO.AMOUNT :
                        record.setAmount(new BigDecimal(value));
                        break;
                    case WalletDAO.DATE :
                        record.setDate(new Date(Long.parseLong(value)));
                        break;
                    case WalletDAO.DESCRIPTION :
                        record.setDescription(value);
                        break;
                    case WalletDAO.INCOME :
                        Integer income = Integer.parseInt(value);
                        if(income > 0){
                            record.setIncome(Boolean.TRUE);
                        }else {
                            record.setIncome(Boolean.FALSE);
                        }
                        break;
                    case WalletDAO.SYNC :
                        Integer sync = Integer.parseInt(value);
                        if(sync > 0){
                            record.setSync(Boolean.TRUE);
                        }else {
                            record.setSync(Boolean.FALSE);
                        }
                        break;
                    case WalletDAO.TYPE :
                        record.setType(value);
                        break;
                    case WalletDAO.UPDATE :
                        record.setUpdate(new Date(Long.parseLong(value)));
                        break;
                    default:
                        break;
                }
            }
            records.add(record);
        }
        return records;
    }
}
