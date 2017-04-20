package com.timeron.timeronwallet.resource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Timeron on 2017-03-04.
 *
 */

public class WalletDatabadeHelper extends SQLiteOpenHelper {

    Context context;

    protected static final String DB_NAME = "WALLET";
    private static final int DB_VERSION = 1;


    public static final String WALLET_RECORD_TABLE = "RECORD";
    public static final String ID = "_id";
    public static final String INCOME = "INCOME";
    public static final String AMOUNT = "AMOUNT";
    public static final String ACCOUNT = "ACCOUNT";
    public static final String DATE = "DATE";
    public static final String UPDATE = "UPDATE_DATE";
    public static final String TYPE = "TYPE";
    public static final String SYNC = "SYNC";
    public static final String DESCRIPTION = "DESCRIPTION";

    public WalletDatabadeHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+WALLET_RECORD_TABLE+" (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AMOUNT+" REAL, " +
                DESCRIPTION+" TEXT, " +
                INCOME+" NUMERIC, " +
                UPDATE+" NUMERIC, " +
                DATE+" NUMERIC, " +
                TYPE+" TEXT, " +
                ACCOUNT+" TEXT, " +
                SYNC+" NUMERIC);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
