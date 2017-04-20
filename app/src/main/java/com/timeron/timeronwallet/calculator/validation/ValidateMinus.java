package com.timeron.timeronwallet.calculator.validation;

import com.timeron.timeronwallet.calculator.validation.validationAbstract.AbstractValidator;
import com.timeron.timeronwallet.constant.CalculatorButton;

/**
 * Created by Timeron on 2017-02-25.
 */

public class ValidateMinus extends AbstractValidator {
    @Override
    public boolean validate(String str, CalculatorButton operation) {
        return true;
    }
}
