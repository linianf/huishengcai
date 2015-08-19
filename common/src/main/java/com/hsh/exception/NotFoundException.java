package com.hsh.exception;

/**
 * 
 * @author lengxiangwu
 *
 */
public class NotFoundException extends Exception {

    private static final long serialVersionUID = 7014265721846407150L;

    private String msg;

    public NotFoundException(String s) {

        super(s);
        this.msg = s;
    }

    public String getMessage() {

        return msg;
    }
}
