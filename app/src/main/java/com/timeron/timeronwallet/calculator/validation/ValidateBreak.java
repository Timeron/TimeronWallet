package com.timeron.timeronwallet.calculator.validation;

import com.timeron.timeronwallet.calculator.validation.validationAbstract.AbstractValidator;
import com.timeron.timeronwallet.constant.CalculatorButton;

/**
 * Created by Timeron on 2017-02-25.
 */

public class ValidateBreak extends AbstractValidator {
    @Override
    public boolean validate(String str, CalculatorButton operation) {
        if(!operation.equals(CalculatorButton.NONE)){
            int indexOfOperation = str.lastIndexOf(operation.getCharacter());
            String value1 = str.substring(indexOfOperation);
            String value2 = str.substring(indexOfOperation+1, str.length()-1);
            if(!checkValue(value1)){
                return false;
            }
            if(!checkValue(value2)){
                return false;
            }
        }else{
            if(!checkValue(str)){
                return false;
            }
        }
        return true;
    }

    private boolean checkValue(String value){
        int i = value.length();
        String x = value.replace(CalculatorButton.BREAK.getCharacter(), "");
        int j = value.replace(CalculatorButton.BREAK.getCharacter(), "").length();
        int breaks = value.length() - value.replace(CalculatorButton.BREAK.getCharacter(), "").length();
        if(breaks > 1){
            return false;
        }
        return true;
    }
}
