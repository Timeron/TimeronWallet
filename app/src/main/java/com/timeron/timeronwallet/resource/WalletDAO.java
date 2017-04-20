package com.timeron.timeronwallet.resource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.timeron.timeronwallet.R;
import com.timeron.timeronwallet.calculator.match.CalculatorMath;
import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.util.ResultImpl;

/**
 * Created by Timeron on 2017-03-04.
 *
 */

public class WalletDAO extends WalletDatabadeHelper{

    public WalletDAO(Context context) {
        super(context);
    }

    public ResultImpl save(WalletRecord walletRecord){
        ResultImpl resultDto = new ResultImpl(walletRecord);
        resultDto = validateBeforeSave(walletRecord, resultDto);
        if(resultDto.isSuccess()) {
            try {
                SQLiteDatabase db = getWritableDatabase();
                db.insert(WALLET_RECORD_TABLE, null, walletRecord.getContenValue());
                for(WalletRecord r : walletRecord.getChildRecords()){
                    db.insert(WALLET_RECORD_TABLE, null, r.getContenValue());
                }
                resultDto.addMessage(R.string.MS_RECORD_ADDED);
                db.close();
                Log.i(WalletDAO.class.getName(), "saved: "+ walletRecord.toString());
            }catch (SQLiteException ex){
                resultDto.addError(R.string.MS_SQLiteException);
            }
        }
        return resultDto;
    }

    public int showNumberOfRecords(){
        int result = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.query(WALLET_RECORD_TABLE, new String[]{"count(_id)"}, null, null, null, null, null);
            if(cursor.moveToFirst()){
                Toast toast = Toast.makeText(context, cursor.getString(0), Toast.LENGTH_LONG);
                toast.show();
            }
            db.close();
            cursor.close();
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Cursor getAllRecords(){
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.query(WALLET_RECORD_TABLE, new String[]{"*"}, null, null, null, null, "_id"+" ASC");
            if(cursor.moveToFirst()){
                Toast toast = Toast.makeText(context, cursor.getString(1), Toast.LENGTH_LONG);
                toast.show();
            }
            db.close();
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
        return cursor;
    }

    public void removeRecord(Long record) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(WALLET_RECORD_TABLE, "_id = ?", new String[]{record.toString()});
        db.close();
    }

    public Cursor getAllNotSynchRecords() {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.query(WALLET_RECORD_TABLE, new String[]{"*"}, WalletDAO.SYNC+" = ?", new String[]{"0"}, null, null, "_id"+" ASC");
            cursor.moveToFirst();
            db.close();
        }catch (SQLiteException ex){
            ex.printStackTrace();
        }
        return cursor;
    }

    public ResultImpl updateRecord(WalletRecord walletRecord) {
        ResultImpl resultDto = new ResultImpl(walletRecord);
        resultDto = validateBeforeSave(walletRecord, resultDto);
        if(resultDto.isSuccess()) {
            try {
                SQLiteDatabase db = getWritableDatabase();
                String condition = "_id = ?";
                db.update(WALLET_RECORD_TABLE, walletRecord.getContenValue(), condition, new String[]{Integer.toString(walletRecord.getId())});
                resultDto.addMessage(R.string.MS_RECORD_ADDED);
                db.close();
                Log.i(WalletDAO.class.getName(), "updated: "+ walletRecord.toString());
            }catch (SQLiteException ex){
                resultDto.addError(R.string.MS_SQLiteException);
            }
        }
        return resultDto;
    }

    private ResultImpl validateBeforeSave(WalletRecord walletRecord, ResultImpl resultDto){
        if(walletRecord.getAmount().equals(CalculatorMath.round(CalculatorMath.getZero()))){
            resultDto.addError(R.string.MS_NO_AMOUNT_VALUE);
            return resultDto;
        }
        if(walletRecord.getDate().getTime() == 0){
            resultDto.addError(R.string.MS_NO_DATA_VALUE);
            return resultDto;
        }
        if(walletRecord.getAccount() == null || walletRecord.getAccount().isEmpty()){
            resultDto.addError(R.string.MS_NO_ACCOUNT_VALUE);
            return resultDto;
        }
        if(walletRecord.getType() == null || walletRecord.getType().isEmpty()){
            resultDto.addError(R.string.MS_NO_TYPE_VALUE);
            return resultDto;
        }
        if(walletRecord.getType() == null || walletRecord.getType().isEmpty()){
            resultDto.addError(R.string.MS_NO_TYPE_VALUE);
            return resultDto;
        }
        return resultDto;
    }



}
