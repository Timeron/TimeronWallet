package com.timeron.timeronwallet.calculator.controllerInterface;

import com.timeron.timeronwallet.exception.WalletValidationException;
import com.timeron.timeronwallet.constant.CalculatorButton;

/**
 * Created by Timeron on 2017-02-25.
 */

public interface CalculatorControllerInt {

    public String handleCalculatorAction(CalculatorButton button) throws WalletValidationException;

    public String getState();

}
