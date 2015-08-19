package com.hsh.exception;

/**
 * 
 * @author lengxiangwu
 * 
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 6219699725973099988L;

    public BaseException() {

        super();
    }

    public BaseException(String message, Throwable cause) {

        super(message, cause);
    }

    public BaseException(String message) {

        super(message);
    }

    public BaseException(Throwable cause) {

        super(cause);
    }
}
