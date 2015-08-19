package com.hsh.exception;

/**
 * 
 * @author lengxiangwu
 * 
 */
public class InvalidParameterException extends BaseException {

    private static final long serialVersionUID = -4369654995034164121L;

    public InvalidParameterException() {

        super();
    }

    public InvalidParameterException(String message, Throwable cause) {

        super(message, cause);
    }

    public InvalidParameterException(String message) {

        super(message);
    }

    public InvalidParameterException(Throwable cause) {

        super(cause);
    }
}
