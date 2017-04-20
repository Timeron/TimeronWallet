package com.timeron.timeronwallet;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.timeron.timeronwallet.constant.CalculatorButton;
import com.timeron.timeronwallet.exception.WalletValidationException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class CalendarActivity extends AppCompatActivity {

    public static final String DATE_PICKER = "datePicker";

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        String startDate = getIntent().getStringExtra(this.DATE_PICKER);

    }

    public void onClickSend(View view) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Date date = getDateFromDatePicker(datePicker);
        Intent intent = new Intent();
        intent.putExtra(DATE_PICKER, date.getTime());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
