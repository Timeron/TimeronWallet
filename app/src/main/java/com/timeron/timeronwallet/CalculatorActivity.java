package com.timeron.timeronwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.timeron.timeronwallet.calculator.controller.CalculatorController;
import com.timeron.timeronwallet.calculator.controllerInterface.CalculatorControllerInt;
import com.timeron.timeronwallet.constant.CalculatorButton;
import com.timeron.timeronwallet.exception.WalletValidationException;

/**
 * Created by Timeron on 2017-02-25.
 *
 */

public class CalculatorActivity extends AppCompatActivity {

    public static String START_VALUE = "startValue";
    public static String RESULT_VALUE = "resultValue";

    private CalculatorControllerInt calculatorController;

    private CalculatorButton clickedButton;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        display = (TextView) findViewById(R.id.mainDisplay);
        String startValue = intent.getStringExtra(START_VALUE);
        this.calculatorController = new CalculatorController(startValue);

        Log.i("Log", "CalculatorControllerInt");
        display.setText(this.calculatorController.getState());
    }

    public void onClickCalculator1(View view) {
        handleDisplay(CalculatorButton.ONE);
    }

    public void onClickCalculator2(View view) {
        handleDisplay(CalculatorButton.TWO);
    }

    public void onClickCalculator3(View view) {
        handleDisplay(CalculatorButton.THREE);
    }

    public void onClickCalculator4(View view) {
        handleDisplay(CalculatorButton.FOUR);
    }

    public void onClickCalculator5(View view) {
        handleDisplay(CalculatorButton.FIVE);
    }

    public void onClickCalculator6(View view) {
        handleDisplay(CalculatorButton.SIX);
    }

    public void onClickCalculator7(View view) {
        handleDisplay(CalculatorButton.SEVEN);
    }

    public void onClickCalculator8(View view) {
        handleDisplay(CalculatorButton.EIGHT);
    }

    public void onClickCalculator9(View view) {
        handleDisplay(CalculatorButton.NINE);
    }

    public void onClickCalculator0(View view) {
        handleDisplay(CalculatorButton.ZERO);
    }

    public void onClickCalculatorBreak(View view) {
        handleDisplay(CalculatorButton.BREAK);
    }

    public void onClickCalculatorC(View view) {
        handleDisplay(CalculatorButton.C);
    }

    public void onClickCalculatorMultiplication(View view) {
        handleDisplay(CalculatorButton.MULTIPLICATION);
    }

    public void onClickCalculatorDivision(View view) {
        handleDisplay(CalculatorButton.DIVISION);
    }

    public void onClickCalculatorPlus(View view) {
        handleDisplay(CalculatorButton.PLUS);
    }

    public void onClickCalculatorMinus(View view) {
        handleDisplay(CalculatorButton.MINUS);
    }

    public void onClickCalculatorSum(View view) {
        handleDisplay(CalculatorButton.RESULT);
    }

    public void onClickCalculatorSend(View view) {
        String result = "";
        clickedButton = CalculatorButton.SEND;
        try {
            result = calculatorController.handleCalculatorAction(clickedButton);
            Intent intent = new Intent();
            intent.putExtra(RESULT_VALUE, result);
            setResult(Activity.RESULT_OK, intent);
            finish();
        } catch (WalletValidationException e) {
            e.printStackTrace();
            display.setText(result);
        }

    }

    public void onClickCalculatorBack(View view) {
        handleDisplay(CalculatorButton.BACK);
    }

    public void onClickCalculatorCE(View view) {
        handleDisplay(CalculatorButton.CE);
    }

    public void onClickCalculatorPercent(View view) {
        handleDisplay(CalculatorButton.PERCENT);
    }


    private void handleDisplay(CalculatorButton clickedButton) {
        try {
            display.setText(calculatorController.handleCalculatorAction(clickedButton));
        } catch (WalletValidationException e) {
            e.printStackTrace();
        }
    }
}
