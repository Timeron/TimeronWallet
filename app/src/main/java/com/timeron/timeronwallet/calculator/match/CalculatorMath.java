package com.timeron.timeronwallet.calculator.match;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Timeron on 2017-02-25.
 */

public class CalculatorMath {

    public static BigDecimal doAdd(BigDecimal value1, BigDecimal value2){
        return round(value1.add(value2));
    }

    public static BigDecimal doSubtraction(BigDecimal value1, BigDecimal value2) {
        return round(value1.subtract(value2));
    }

    public static BigDecimal doMultiplication(BigDecimal value1, BigDecimal value2) {
        return round(value1.multiply(value2));
    }

    public static BigDecimal doDivision(BigDecimal value1, BigDecimal value2) {
        return round(value1).divide(round(value2), 2, RoundingMode.HALF_UP); //round(value1.divide(value2), 2);
    }

    public static BigDecimal getZero() {
        return round(new BigDecimal("0"));
    }

    public static BigDecimal round(BigDecimal d) {
        d = d.setScale(2, BigDecimal.ROUND_HALF_UP);
        return d;
    }

    public static BigDecimal doPercent(BigDecimal value1, BigDecimal value2) {
        return round(value1.multiply(value2).divide(new BigDecimal(100)));
    }
}
