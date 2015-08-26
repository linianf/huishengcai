package com.hsh.dao;

import java.util.List;
import java.util.Map;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;

public interface MessageDao {

    void addMessage(Message message) throws DataValidateException;

    void updateMessage(Message message) throws DataValidateException;

    List <Message> listMessage(Map <String, Object> params) throws DataValidateException;

    List <Message> getMessageListByParams(Map <String, Object> params) throws DataValidateException;

}
