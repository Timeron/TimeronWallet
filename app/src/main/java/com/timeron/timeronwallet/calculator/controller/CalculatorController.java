package com.timeron.timeronwallet.calculator.controller;

import com.timeron.timeronwallet.exception.WalletValidationException;
import com.timeron.timeronwallet.calculator.controllerInterface.CalculatorControllerInt;
import com.timeron.timeronwallet.calculator.match.CalculatorMath;
import com.timeron.timeronwallet.calculator.validation.ValidateBreak;
import com.timeron.timeronwallet.calculator.validation.ValidateDivision;
import com.timeron.timeronwallet.calculator.validation.ValidateMinus;
import com.timeron.timeronwallet.calculator.validation.ValidateMultiplication;
import com.timeron.timeronwallet.calculator.validation.ValidateNumber;
import com.timeron.timeronwallet.calculator.validation.ValidatePercent;
import com.timeron.timeronwallet.calculator.validation.ValidatePlus;
import com.timeron.timeronwallet.calculator.validation.validationInterface.ValidationInt;
import com.timeron.timeronwallet.constant.CalculatorButton;

import java.math.BigDecimal;

/**
 * Created by Timeron on 2017-02-25.
 */

public class CalculatorController implements CalculatorControllerInt {

    private String state;
    private String previousState;
    private CalculatorButton operation = CalculatorButton.NONE;
    private CalculatorButton previousOperation = CalculatorButton.NONE;

    public CalculatorController(){
        this.state = CalculatorMath.getZero().toString();
        this.previousState = CalculatorMath.getZero().toString();
    };

    public CalculatorController(String state){
        if(state == null || state.isEmpty()){
            this.state = CalculatorMath.getZero().toString();
        }else {
            this.state = state;
        }
        this.previousState = CalculatorMath.getZero().toString();
    }

    @Override
    public String handleCalculatorAction(CalculatorButton button) throws WalletValidationException{
        ValidationInt validator;
        if(button.isOperation()){
            switch (button){
                case BREAK:
                    validator = new ValidateBreak();
                    this.state = addToString(this.state, button, validator);
                    break;
                case DIVISION:
                    validator = new ValidateDivision();
                    addOperation(button, validator);
                    break;
                case MULTIPLICATION:
                    validator = new ValidateMultiplication();
                    addOperation(button, validator);
                    break;
                case MINUS:
                    validator = new ValidateMinus();
                    addOperation(button, validator);
                    break;
                case PLUS:
                    validator = new ValidatePlus();
                    addOperation(button, validator);
                    break;
                case PERCENT:
                    validator = new ValidatePercent();
                    addOperation(button, validator);
                    break;
                case RESULT:
                    getResult();
                    break;
                case BACK:
                    state = removeLastCharacter();
                    break;
                case C:
                    state = CalculatorMath.getZero().toString();
                    break;
                case CE:
                    state = previousState;
                    operation = previousOperation;
                    break;
                case SEND:
                    if(!this.operation.equals(CalculatorButton.NONE)){
                        boolean result = handleOperation();
                        if(!result){
                            throw new WalletValidationException("Nie można przesłać wartości");
                        }
                    }else {
                        state = CalculatorMath.round(new BigDecimal(state)).toString();
                    }
                    break;
                default:
                    break;
            }

        }else{
            validator = new ValidateNumber();
            this.state = addToString(this.state, button, validator);
        }
        return this.state;
    }

    @Override
    public String getState() {
        return this.state;
    }

    private void getResult() {
        if(!this.operation.equals(CalculatorButton.NONE)){
            boolean result = handleOperation();
            if(result) {
                this.operation = CalculatorButton.NONE;
            }
        }else{
            state = CalculatorMath.round(new BigDecimal(state)).toString();
        }
    }

    private void addOperation(CalculatorButton button, ValidationInt validator) {
        if(!state.equals(CalculatorMath.getZero().toString())) {
            if (this.operation.equals(CalculatorButton.NONE)) {
                if (button.isOperation()) {
                    this.operation = button;
                }
                this.state = addToString(state, button, validator);
            } else {
                boolean result = handleOperation();
                if(result) {
                    this.state = addToString(state, button, validator);
                    this.operation = button;
                }
            }
        }
    }

    private boolean handleOperation() {
        String tempState = state;
        if(state.contains(operation.getCharacter())){
            if(state.lastIndexOf(operation.getCharacter()) == state.length()-1){
                state = removeLastCharacter();
            }else {
                BigDecimal result = new BigDecimal(0);
                StringBuilder str = new StringBuilder(state);
                int operatorIndex = str.lastIndexOf(operation.getCharacter());
                BigDecimal value1 = new BigDecimal(str.substring(0, operatorIndex));
                BigDecimal value2 = new BigDecimal(str.substring(operatorIndex + 1));

                switch (operation){
                    case MULTIPLICATION:
                        result = CalculatorMath.doMultiplication(value1, value2);
                        break;
                    case DIVISION:
                        if(!value2.equals(new BigDecimal(0))) {
                            result = CalculatorMath.doDivision(value1, value2);
                        }else {
                            result = null;
                        }
                        break;
                    case PLUS:
                        result = CalculatorMath.doAdd(value1, value2);
                        break;
                    case MINUS:
                        result = CalculatorMath.doSubtraction(value1, value2);
                        break;
                    case PERCENT:
                        result = CalculatorMath.doPercent(value1, value2);
                        break;
                }

                if(result != null) {
                    state = result.toString();
                    previousState = tempState;
                    previousOperation = operation;
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private String removeLastCharacter() {
        StringBuilder str = new StringBuilder(state);
        if(state.length() == 1){
            return CalculatorMath.getZero().toString();
        }else {
            return str.deleteCharAt(str.length() - 1).toString();
        }
    }

    private String addToString(String text, CalculatorButton button, ValidationInt validator) {
        String oldText = text;
        if(text.equals(CalculatorMath.getZero().toString())) {
            text = "";
        }
        StringBuilder str = new StringBuilder(text);
        text = str.append(button.getCharacter()).toString();
        if(validator.validate(text, operation)){
            return text;
        }
        return oldText;
    }
}
