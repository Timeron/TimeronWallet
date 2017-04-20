package com.timeron.timeronwallet.actitityModule;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.timeron.timeronwallet.resource.WalletDAO;

/**
 * Created by Timeron on 2017-03-12.
 */

public class RecordRowActivity extends ListActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            TextView test = (TextView) findViewById()
            String test = savedInstanceState.getString(WalletDAO.AMOUNT);
            Log.i("Test: ", test);
        }
}
