package com.timeron.timeronwallet.service.entity;

        import android.content.ContentValues;

        import com.timeron.timeronwallet.calculator.match.CalculatorMath;
        import com.timeron.timeronwallet.resource.WalletDatabadeHelper;
        import com.timeron.timeronwallet.service.entity.interf.Entity;
        import com.timeron.timeronwallet.util.interf.AvailableForResult;

        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

/**
 * Created by Timeron on 2017-03-04.
 *
 */

public class WalletRecord implements AvailableForResult, Entity {

    public static final String INCOME_FALSE = "Wydatek";
    public static final String INCOME_TRUE = "Dochód";

    private int id;
    private Boolean income;
    private Date date;
    private Date update;
    private String account;
    private BigDecimal amount;
    private String description;
    private String type;
    private boolean sync;
    private List<WalletRecord> childRecords = new ArrayList<>();

    public WalletRecord() {
        this.amount = CalculatorMath.getZero();
        date = new Date();
    }

    @Override
    public ContentValues getContenValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(WalletDatabadeHelper.INCOME, this.income);
        contentValues.put(WalletDatabadeHelper.AMOUNT, this.amount.toString());
        contentValues.put(WalletDatabadeHelper.ACCOUNT, this.account);
        contentValues.put(WalletDatabadeHelper.DATE, this.date.getTime());
        if(this.update == null) {
            contentValues.put(WalletDatabadeHelper.UPDATE, 0);
        }else{
            contentValues.put(WalletDatabadeHelper.UPDATE, this.update.getTime());
        }
        contentValues.put(WalletDatabadeHelper.TYPE, this.type);
        contentValues.put(WalletDatabadeHelper.SYNC, this.sync);
        contentValues.put(WalletDatabadeHelper.DESCRIPTION, this.description);
        return contentValues;
    }

    /*
    Getters & Setters
     */

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WalletRecord> getChildRecords() {
        if(childRecords == null){
            childRecords = Collections.emptyList();
        }
        return childRecords;
    }

    public void addChildRecord(WalletRecord walletRecord){
        if(childRecords == null){
            childRecords = new ArrayList<>();
        }
        childRecords.add(walletRecord);
    }

    @Override
    public String toString() {
        return "WalletRecord{" +
                "id=" + id +
                ", income=" + income +
                ", date=" + date +
                ", update=" + update +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", sync=" + sync +
                '}';
    }

    private Map<String, String> getAsMap(){
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount.toString());
        map.put("type", type);
        return map;
    }

    public List<Map<String,String>> getChildRecordMap() {
        List<Map<String, String>> list = new ArrayList<>();
        for(WalletRecord child : getChildRecords()){
            list.add(child.getAsMap());
        }
        return list;
    }

    public boolean removeChild(String amountStr, String typeStr) {
        WalletRecord recourdToRemove = null;
        for(WalletRecord r : childRecords){
            if(r.amount.equals(new BigDecimal(amountStr)) && r.type.equals(typeStr)){
                recourdToRemove = r;
            }
        }
        if(recourdToRemove != null) {
            childRecords.remove(recourdToRemove);
            return true;
        }
        return false;
    }

}
