package com.hsh.dao;

import java.util.List;
import java.util.Map;

import com.hsh.exception.DataValidateException;
import com.hsh.model.PushLog;

public interface PushLogDao {

    void addPushLog(PushLog pushLog) throws DataValidateException;

    /**
     * 根据条件分页查询pushLog
     * 
     * @param params
     *  messageId 消息id 
     *  pageSize 条数
     *  minId 最小id
     * @return
     */
    List <PushLog> pagePushLogByMessageId(Map <String, Object> params) throws DataValidateException;

}
