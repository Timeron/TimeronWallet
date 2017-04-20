package com.timeron.timeronwallet.calculator;

import com.timeron.timeronwallet.exception.WalletValidationException;
import com.timeron.timeronwallet.calculator.controller.CalculatorController;
import com.timeron.timeronwallet.constant.CalculatorButton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Timeron on 2017-02-21.
 *
 */

public class CalculatorControllerTest {

    @Test
    public void handleCalculatorActionTest_ButtonDIVISIONby0Result() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("4/0", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonDIVISIONby0_MultipleResult() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);
        calculatorController.handleCalculatorAction(CalculatorButton.RESULT);
        calculatorController.handleCalculatorAction(CalculatorButton.RESULT);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("4/0", display);
    }

    @Test
    public void handleCalculatorActionTest_AddOperationTwice() throws WalletValidationException {
        String tempState = "75";
        CalculatorController calculatorController = new CalculatorController(tempState);
        String display;
        display = calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        assertEquals(tempState + CalculatorButton.PLUS.getCharacter(), display);
        display = calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        assertEquals(tempState + CalculatorButton.MINUS.getCharacter(), display);
    }

    @Test
    public void handleCalculatorActionTest_AddOperationOneByOne() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        assertEquals("98.00+13", display);
    }

    @Test
    public void handleCalculatorActionTest_getResult() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("111.00", display);
    }

    @Test
    public void handleCalculatorActionTest_removeOperation() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.BACK);

        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("60.00", display);
    }

    @Test
    public void handleCalculatorActionTest_removeOperationAfterCalculation() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.BACK);

        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("83.00", display);
    }

    @Test
    public void handleCalculatorActionTest_multiplication() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.MULTIPLICATION);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("1725.00", display);
    }

    @Test
    public void handleCalculatorActionTest_division() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("3.26", display);
    }

    /**
     * Decimal
     */

    @Test
    public void handleCalculatorActionTest_decimalButtonBreak() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("75.23", display);
    }

    @Test
    public void handleCalculatorActionTest_addDecimal() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);

        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("51.48", display);
    }

    @Test
    public void handleCalculatorActionTest_addLongDecimal() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);

        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("51.48", display);
    }

    @Test
    public void handleCalculatorActionTest_operationOnNegative() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);

        display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("1.00", display);
    }

    @Test
    public void handleCalculatorActionTest_0IfNoValue() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        display = calculatorController.handleCalculatorAction(CalculatorButton.C);

        assertEquals("0.00", display);
    }

    @Test
    public void handleCalculatorActionTest_cleanFirst0AfterClickingNumber() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        display = calculatorController.handleCalculatorAction(CalculatorButton.C);

        assertEquals("0.00", display);
    }

    @Test
    public void handleCalculatorActionTest_canNotStartFromOperationStartingApp() throws WalletValidationException {
        String display = "";
        CalculatorController calculatorController = new CalculatorController(display);

        display = calculatorController.handleCalculatorAction(CalculatorButton.PLUS);

        assertEquals("0.00", display);
    }

    @Test
    public void handleCalculatorActionTest_canNotStartFromOperationWorkingApp() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.PLUS);

        assertEquals("0.00", display);
    }

    @Test
    public void handleCalculatorActionTest_Remove0AfterClickingNumber() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.ONE);

        assertEquals("1", display);
    }

    @Test
    public void handleCalculatorActionTest_Remove0AfterClickingNumberAndResult() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        calculatorController.handleCalculatorAction(CalculatorButton.C);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("1.00", display);
    }

    @Test
    public void handleCalculatorActionTest_Adding0AfterOperation() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.ZERO);

        assertEquals("23+0", display);
    }

    @Test
    public void handleCalculatorActionTest_addingBreakOneValue() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.BREAK);

        assertEquals("23.1", display);
    }

    @Test
    public void handleCalculatorActionTest_addingBreakTwoValues() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);

        String display = calculatorController.handleCalculatorAction(CalculatorButton.BREAK);

        assertEquals("23.1+3.7", display);
    }

    @Test
    public void handleCalculatorActionTest_addingBreakTwoValuesAndNegation() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.RESULT);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.BREAK);

        assertEquals("-10.6077", display);
    }

    @Test
    public void handleCalculatorActionTest_addingTwoBreaks() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);

        assertEquals("23.7", display);
    }

    @Test
    public void handleCalculatorActionTest_addingOperationAfretNegativeValue() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();

        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.EIGHT);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        String display = calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);

        assertEquals("-6.60/", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonPercent() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);
        calculatorController.handleCalculatorAction(CalculatorButton.PERCENT);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.BREAK);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);

        CalculatorButton button = CalculatorButton.RESULT;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("0.25", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonCE() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.MINUS);
        calculatorController.handleCalculatorAction(CalculatorButton.CE);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.RESULT);

        assertEquals("49.00", display);
    }

    @Test
    public void handleCalculatorActionTest_SimpleValueSend() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.SEND);

        assertEquals("12.00", display);
    }

    @Test
    public void handleCalculatorActionTest_OperationAfterSend() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.PLUS);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.SEND);

        assertEquals("15.00", display);
    }
}
