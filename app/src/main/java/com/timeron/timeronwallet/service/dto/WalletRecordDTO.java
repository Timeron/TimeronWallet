package com.timeron.timeronwallet.service.dto;

import android.content.ContentValues;

import com.timeron.timeronwallet.constant.Account;
import com.timeron.timeronwallet.constant.Type;
import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.service.entity.interf.Entity;
import com.timeron.timeronwallet.util.interf.AvailableForResult;

/**
 * Created by Timeron on 2017-04-01.
 */

public class WalletRecordDTO implements AvailableForResult{

    private int id = 0;
    private float value = 0;
    private String description = "";
    private boolean income = false;
    private boolean transfer = false;
    private long date = 0;
    private long updated = 0;
    private int recordTypeId = 0;
    private int accountId = 0;
    private int destynationAccountId = 0;
    private int sourceWalletAccountId = 0;

    public WalletRecordDTO(WalletRecord record){
        this.id = id;
        this.value = record.getAmount().floatValue();
        this.description = record.getDescription();
        this.income = record.getIncome();
//        this.transfer = record.
        this.date = record.getDate().getTime();
        this.updated = record.getUpdate().getTime();
        Type type = transferToType(record.getType());
        Account account = transferToAccount(record.getAccount());
        if(type != null) {
            this.recordTypeId = type.getId();
        }
        if(account != null) {
            this.accountId = account.getId();
        }
//        this.destynationAccountId = record
//        this.sourceWalletAccountId = record
    }

    private Account transferToAccount(String accountName){
        for(Account account : Account.values()){
            if(account.getName().equals(accountName)){
                return account;
            }
        }
        return null;
    }

    private Type transferToType(String typeName){
        for(Type type : Type.values()){
            if(type.getName().equals(typeName)){
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public int getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(int recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getDestynationAccountId() {
        return destynationAccountId;
    }

    public void setDestynationAccountId(int destynationAccountId) {
        this.destynationAccountId = destynationAccountId;
    }

    public int getSourceWalletAccountId() {
        return sourceWalletAccountId;
    }

    public void setSourceWalletAccountId(int sourceWalletAccountId) {
        this.sourceWalletAccountId = sourceWalletAccountId;
    }
}
