package com.timeron.timeronwallet.calculator.calculatorSuite;

import com.timeron.timeronwallet.calculator.CalculatorControllerButtonTest;
import com.timeron.timeronwallet.calculator.CalculatorControllerConstructorTest;
import com.timeron.timeronwallet.calculator.CalculatorControllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Timeron on 2017-02-24.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorControllerButtonTest.class,
        CalculatorControllerTest.class,
        CalculatorControllerConstructorTest.class})
public class CalculatorSuite {
}
