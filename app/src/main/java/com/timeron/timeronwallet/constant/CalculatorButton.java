package com.timeron.timeronwallet.constant;

/**
 * Created by Timeron on 2017-02-25.
 */

public enum CalculatorButton {
    ONE("1", false), TWO("2", false), THREE("3", false), FOUR("4", false), FIVE("5", false), SIX("6", false), SEVEN("7", false), EIGHT("8", false), NINE("9", false), ZERO("0", false),
    BREAK(".", true), RESULT("=", true), PLUS("+", true), MINUS("-", true), MULTIPLICATION("*", true), DIVISION("/", true), PERCENT("%", true),
    C("", true), CE("", true), BACK("", true), SEND("", true), NONE("", true);

    private String character;
    private boolean operation;

    CalculatorButton(String button, boolean operation){
        this.character = button;
        this.operation = operation;
    }

    public String getCharacter(){
        return this.character;
    }

    public boolean isOperation(){
        return this.operation;
    }

}
