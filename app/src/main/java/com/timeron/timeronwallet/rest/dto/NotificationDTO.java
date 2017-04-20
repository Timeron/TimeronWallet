package com.timeron.timeronwallet.rest.dto;

import java.util.List;

/**
 * Created by Timeron on 2017-03-31.
 *
 */

public class NotificationDTO {

    private boolean success;
    private List<String> messages;
    private List<String> errors;

    public String getFirstMessage() {
        return messages.get(0);
    }

    public String getMessagesAsString(String separator) {
        StringBuilder result = new StringBuilder("");
        for(String message : messages){
            result.append(message);
            result.append(separator);
        }
        result.delete(result.length()-separator.length()-1, result.length()-1);
        return result.toString();
    }

    public String getErrorsAsString(String separator) {
        StringBuilder result = new StringBuilder("");
        for(String error : errors){
            result.append(error);
            result.append(separator);
        }
        result.delete(result.length()-separator.length()-1, result.length()-1);
        return messages.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
