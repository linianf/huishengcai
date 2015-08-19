package com.hsh.exception;

/**
 * 
 * @author lengxiangwu
 *
 */
public class DatabaseException extends BaseException {

    private static final long serialVersionUID = 8684464810621058242L;

    public DatabaseException() {

        super();
    }

    public DatabaseException(String message, Throwable cause) {

        super(message, cause);
    }

    public DatabaseException(String message) {

        super(message);
    }

    public DatabaseException(Throwable cause) {

        super(cause);
    }
}
