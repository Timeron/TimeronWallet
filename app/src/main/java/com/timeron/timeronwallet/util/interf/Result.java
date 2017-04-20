package com.timeron.timeronwallet.util.interf;

import android.content.Intent;

import java.util.List;

/**
 * Created by Timeron on 2017-03-05.
 *
 */

public interface Result {

    public boolean isSuccess();
    public List<Integer> getErrors();
    public List<Integer> getMessages();
    public void addError(int error);
    public void addMessage(int message);

}
