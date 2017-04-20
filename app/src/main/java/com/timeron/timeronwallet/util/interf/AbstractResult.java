package com.timeron.timeronwallet.util.interf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Timeron on 2017-03-05.
 *
 */

public abstract class AbstractResult implements Result {

    protected boolean success = true;
    protected AvailableForResult object;
    protected List<Integer> messages;
    protected List<Integer> errors;

    public AbstractResult(AvailableForResult object){
        this.object = (AvailableForResult) object;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Integer> getErrors() {
        if(errors == null){
            return Collections.emptyList();
        }
        return errors;
    }

    public List<Integer> getMessages() {
        if(messages == null){
            return Collections.emptyList();
        }
        return messages;
    }

    public void addError(int error) {
        if(errors == null){
            errors = new ArrayList<>();
        }
        errors.add(error);
        success = false;

    }

    public void addMessage(int message) {
        if(messages == null){
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public Integer getFirstError(){
        if(errors.isEmpty()){
            return null;
        }else {
            return errors.get(0);
        }
    }

    public Integer getFirstMessage(){
        if(messages.isEmpty()){
            return null;
        }else {
            return messages.get(0);
        }
    }
}
