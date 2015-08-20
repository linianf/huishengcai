package com.hsh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ABaseController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public String getCurrentUsername() {

        return "";
    }

    public long getCurrentUserId() {

        return 0L;
    }
}
