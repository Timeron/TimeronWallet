package com.timeron.timeronwallet.calculator;

import com.timeron.timeronwallet.calculator.controller.CalculatorController;
import com.timeron.timeronwallet.calculator.match.CalculatorMath;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Timeron on 2017-03-01.
 */

public class CalculatorControllerConstructorTest {

    @Test
    public void Constructor_noParameter(){
        CalculatorController controller = new CalculatorController();
        assertEquals(CalculatorMath.getZero().toString(), controller.getState().toString());
    }

    @Test
    public void Constructor_parameter(){
        String state = "35.00";
        CalculatorController controller = new CalculatorController(state);
        assertEquals(state, controller.getState().toString());
    }

}
