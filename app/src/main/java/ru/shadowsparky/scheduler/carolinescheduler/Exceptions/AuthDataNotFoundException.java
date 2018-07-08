/*
 * Copyright (c) 2018.
 * Samsonov_Eugene, AVB Inc.
 */

package ru.shadowsparky.scheduler.carolinescheduler.Exceptions;

public class AuthDataNotFoundException extends Exception {

    private String _error;
    @Override
    public String getMessage() {
        return _error;
    }

    public AuthDataNotFoundException(String error){
        this._error = error;
    }
}