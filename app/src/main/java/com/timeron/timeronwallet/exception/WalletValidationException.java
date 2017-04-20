package com.timeron.timeronwallet.exception;

/**
 * Created by Timeron on 2017-02-28.
 */

public class WalletValidationException extends Exception {

    public WalletValidationException(){
        super();
    }

    public WalletValidationException(String message){
        super(message);
    }

}
