package com.hsh.service;

import java.util.List;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;
import com.hsh.model.PushLog;

public interface PushLogService {

    /**
     * 增加push日志信息
     * @param pushMessageLog
     * @throws DataValidateException
     */
    void addPushLog(Message message) throws DataValidateException;

    List <PushLog> pagePushLogByMessageId(long messageId, Integer pageSize, Integer minId) throws DataValidateException;
}
