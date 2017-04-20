package com.timeron.timeronwallet.constant;

/**
 * Created by Timeron on 2017-02-25.
 */

public enum ValidationRegEx {
    ADD_BREAK("");

    private String regEx;

    ValidationRegEx(String regEx){
        this.regEx = regEx;
    }

    public String getValue(){
        return this.regEx;
    }
}
