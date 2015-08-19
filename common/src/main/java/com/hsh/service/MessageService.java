package com.hsh.service;

import java.util.List;
import java.util.Map;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;

public interface MessageService {

    /**
     * 增加message
     * 
     * @param message
     * @throws DataValidateException
     */
    void addMessage(Message message) throws DataValidateException;

    /***
     * 修改message
     * 
     * @param message
     * @throws DataValidateException
     */
    void updateMessage(Message message) throws DataValidateException;

    /**
     * 
     * @param params
     *          pageSize  每页显示条数
     *          minId   最小id
     * @return list
     * @throws DataValidateException
     */
    List<Message> listMessage(Map <String, Object> params) throws DataValidateException;
}
