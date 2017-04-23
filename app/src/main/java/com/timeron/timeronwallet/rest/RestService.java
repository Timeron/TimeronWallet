package com.timeron.timeronwallet.rest;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.timeron.timeronwallet.R;
import com.timeron.timeronwallet.constant.MessageResource;
import com.timeron.timeronwallet.rest.dto.NotificationDTO;
import com.timeron.timeronwallet.service.dto.WalletRecordDTO;
import com.timeron.timeronwallet.util.ResultImpl;
import com.timeron.timeronwallet.util.interf.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Timeron on 2017-03-12.
 *
 */

public class RestService {

    //Prod
    private static final String SERVER = "http://timeron.ddns.net:8080/nexus/v1/walletAndroid/";
    //Test
//    private static final String SERVER = "http://192.168.1.224:8080/nexus/v1/walletAndroid/";
    private Context context;
    RequestQueue queue;

    Gson gson = new Gson();

    public RestService(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public Result serverAvailability(){
        RequestQueue queue = Volley.newRequestQueue(context);
        Result result = new ResultImpl(null);
        String method = "availability";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER+method,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        NotificationDTO notification = gson.fromJson(response, NotificationDTO.class);
                        if(notification.getFirstMessage().equals(MessageResource.SERVER_ID)){
                            Toast toast = Toast.makeText(context, MessageResource.SERVER_WORKS, Toast.LENGTH_SHORT);
                            toast.show();
                        }else {
                            Toast toast = Toast.makeText(context, MessageResource.SERVER_UNKNOWN, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(context, "That didn't work!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        queue.add(stringRequest);
        return result;
    }

    public Result syncRecord(WalletRecordDTO record, Response.Listener responseListener) {
        final Result syncRecordResult = new ResultImpl(record);
        String method = "addNewRecord";
        JSONObject json = null;
        try {
            String gs = gson.toJson(record);
            json = new JSONObject(gson.toJson(record));

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(SERVER+method, json, responseListener, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    syncRecordResult.addError(R.string.ERR_REST_ERROR);

                }
        });

        queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
            syncRecordResult.addError(R.string.ERR_JSON_PARSER_EXCEPTION);
        }
        return syncRecordResult;
    }


}
