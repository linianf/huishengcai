package com.hsh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.MessageDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;
import com.hsh.service.MessageService;
import com.hsh.service.PushLogService;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private PushLogService pushLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMessage(Message message) throws DataValidateException {

        messageDao.addMessage(message);
        if (message.isImmediately()) {
            pushLogService.addPushLog(message);
            message.setState(Message.STATE_SENT);
            messageDao.updateMessage(message);
            // TODO:LENGXW add push method
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessage(Message message) throws DataValidateException {

        messageDao.updateMessage(message);
        if (message.isImmediately()) {
            pushLogService.addPushLog(message);
            message.setState(Message.STATE_SENT);
            messageDao.updateMessage(message);
            // TODO:LENGXW add push method
        }
    }

    @Override
    public List <Message> listMessage(Map <String, Object> params) throws DataValidateException {

        return messageDao.listMessage(params);
    }

    @Override
    public Message getMessageById(long parseLong) throws DataValidateException {

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List <Message> getMessageListByParams(Map <String, Object> params) throws DataValidateException {

        return messageDao.getMessageListByParams(params);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushMessage(Message message) throws DataValidateException{
        message.setState(Message.STATE_SENT);
        this.updateMessage(message);
    }

}
