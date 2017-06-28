package com.sport.exception;

/**
 * Created by EKO-LKB on 2017/3/17.
 */
public class SportsException extends RuntimeException{
    public SportsException(String message, Throwable cause){
        super(message, cause);
    }
    public SportsException(String message){
        super(message);
    }
}
