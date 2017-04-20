package com.timeron.timeronwallet.resource.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.timeron.timeronwallet.service.entity.WalletRecord;
import com.timeron.timeronwallet.resource.WalletDAO;
import com.timeron.timeronwallet.util.ResultImpl;

/**
 * Created by Timeron on 2017-03-05.
 */

public class SaveRecordAsyncTask extends AsyncTask<WalletRecord, Void, ResultImpl> {

    WalletDAO walletDAO;
    Context context;

    public SaveRecordAsyncTask(Context context){
        walletDAO = new WalletDAO(context);
        this.context = context;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected ResultImpl doInBackground(WalletRecord... params) {
        return walletDAO.save(params[0]);
    }

    @Override
    protected void onPostExecute(ResultImpl resultDTO){
        Toast toast;
        if(resultDTO.isSuccess()){
            if(resultDTO.getFirstMessage() != null) {
                toast = Toast.makeText(context, resultDTO.getFirstMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }else{
            toast = Toast.makeText(context, resultDTO.getFirstError(), Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
