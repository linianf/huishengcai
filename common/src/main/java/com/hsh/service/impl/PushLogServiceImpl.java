package com.hsh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsh.dao.PushLogDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;
import com.hsh.model.PushLog;
import com.hsh.service.PushLogService;
import com.hsh.util.HSHUtil;

@Service(value = "pushLogService")
public class PushLogServiceImpl implements PushLogService {

    @Autowired
    private PushLogDao pushLogDao;

    @Override
    public void addPushLog(Message message) throws DataValidateException {

        pushLogDao.addPushLog(new PushLog(message.getUserId(), HSHUtil.now(), message.getContent()));
    }

    @Override
    public List <PushLog> pagePushLogByMessageId(long messageId, Integer pageSize, Integer minId) throws DataValidateException {

        Map <String, Object> params = new HashMap <String, Object>();
        params.put("messageId", messageId);
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        if (minId != null && minId > 0) {
            params.put("minId", minId);
        }

        return pushLogDao.pagePushLogByMessageId(params);
    }
}
