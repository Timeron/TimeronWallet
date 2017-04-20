package com.timeron.timeronwallet.calculator;

import com.timeron.timeronwallet.exception.WalletValidationException;
import com.timeron.timeronwallet.calculator.controller.CalculatorController;
import com.timeron.timeronwallet.constant.CalculatorButton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Timeron on 2017-02-24.
 *
 */

public class CalculatorControllerButtonTest {

    /**
     * Buttons
     */

    @Test
    public void handleCalculatorAction_Button1Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.ONE;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("1", CalculatorButton.ONE.getCharacter());
        assertEquals(CalculatorButton.ONE.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button2Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.TWO;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("2", CalculatorButton.TWO.getCharacter());
        assertEquals(CalculatorButton.TWO.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button3Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.THREE;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("3", CalculatorButton.THREE.getCharacter());
        assertEquals(CalculatorButton.THREE.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button4Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.FOUR;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("4", CalculatorButton.FOUR.getCharacter());
        assertEquals(CalculatorButton.FOUR.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button5Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.FIVE;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("5", CalculatorButton.FIVE.getCharacter());
        assertEquals(CalculatorButton.FIVE.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button6Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.SIX;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("6", CalculatorButton.SIX.getCharacter());
        assertEquals(CalculatorButton.SIX.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button7Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.SEVEN;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("7", CalculatorButton.SEVEN.getCharacter());
        assertEquals(CalculatorButton.SEVEN.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button8Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.EIGHT;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("8", CalculatorButton.EIGHT.getCharacter());
        assertEquals(CalculatorButton.EIGHT.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button9Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.NINE;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("9", CalculatorButton.NINE.getCharacter());
        assertEquals(CalculatorButton.NINE.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_Button0Test() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        CalculatorButton button = CalculatorButton.ZERO;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("0", CalculatorButton.ZERO.getCharacter());
        assertEquals(CalculatorButton.ZERO.getCharacter(), display);
    }

    @Test
    public void handleCalculatorAction_ButtonMULTIPLICATIONTest() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.MULTIPLICATION);

        assertEquals("*", CalculatorButton.MULTIPLICATION.getCharacter());
        assertEquals("4"+CalculatorButton.MULTIPLICATION.getCharacter(), display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonDIVISION() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);

        assertEquals("/", CalculatorButton.DIVISION.getCharacter());
        assertEquals("4"+CalculatorButton.DIVISION.getCharacter(), display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonDIVISIONby0() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        calculatorController.handleCalculatorAction(CalculatorButton.DIVISION);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.PLUS);

        assertEquals("4/0", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonPLUS() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.PLUS);

        assertEquals("+", CalculatorButton.PLUS.getCharacter());
        assertEquals("4"+CalculatorButton.PLUS.getCharacter(), display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonMINUS() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        String display = calculatorController.handleCalculatorAction(CalculatorButton.MINUS);

        assertEquals("-", CalculatorButton.MINUS.getCharacter());
        assertEquals("4"+CalculatorButton.MINUS.getCharacter(), display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonRESULT() throws WalletValidationException {
        String tempState = "12";
        CalculatorController calculatorController = new CalculatorController(tempState);
        CalculatorButton button = CalculatorButton.RESULT;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals(tempState+".00", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonBack() throws WalletValidationException {
        String tempState = "12";
        CalculatorController calculatorController = new CalculatorController(tempState);
        CalculatorButton button = CalculatorButton.BACK;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("1", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonC() throws WalletValidationException {
        String tempState = "12";
        CalculatorController calculatorController = new CalculatorController(tempState);
        CalculatorButton button = CalculatorButton.C;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("0.00", display);
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

        CalculatorButton button = CalculatorButton.CE;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("12+32", display);
    }

    @Test
    public void handleCalculatorActionTest_ButtonPercent() throws WalletValidationException {
        CalculatorController calculatorController = new CalculatorController();
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);
        calculatorController.handleCalculatorAction(CalculatorButton.ZERO);

        CalculatorButton button = CalculatorButton.PERCENT;
        String display = calculatorController.handleCalculatorAction(button);

        assertEquals("100%", display);
    }

    @Test
    public void handleCalculatorActionTest_AllButtons() throws WalletValidationException {
        String tempState = "75";
        CalculatorController calculatorController = new CalculatorController(tempState);
        String display;
        calculatorController.handleCalculatorAction(CalculatorButton.BACK);
        calculatorController.handleCalculatorAction(CalculatorButton.ONE);
        calculatorController.handleCalculatorAction(CalculatorButton.TWO);
        calculatorController.handleCalculatorAction(CalculatorButton.THREE);
        calculatorController.handleCalculatorAction(CalculatorButton.FOUR);
        calculatorController.handleCalculatorAction(CalculatorButton.FIVE);
        calculatorController.handleCalculatorAction(CalculatorButton.SIX);
        calculatorController.handleCalculatorAction(CalculatorButton.SEVEN);
        calculatorController.handleCalculatorAction(CalculatorButton.EIGHT);
        calculatorController.handleCalculatorAction(CalculatorButton.NINE);
        display = calculatorController.handleCalculatorAction(CalculatorButton.ZERO);

        assertEquals("71234567890", display);
    }

}
