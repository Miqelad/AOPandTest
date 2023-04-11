package com.miki.testExample.exceptions;

public class NotFoundUser extends RuntimeException{
    @Override
    public String getMessage() {
        return "Not Found user";
    }
}
