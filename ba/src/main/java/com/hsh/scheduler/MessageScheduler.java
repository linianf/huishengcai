package com.hsh.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;
import com.hsh.service.MessageService;
import com.hsh.util.HSHUtil;

public class MessageScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageScheduler.class);

    @Value("${scheduler.enable}")
    private boolean isEnable;

    @Autowired
    private MessageService messageService;

    /**
     * 每20分钟执行一次
     */
    public void execute() {

        if (!isEnable) {
            LOGGER.info("OrderScheduler 定时任务开关关闭");
            return;
        }
        // 获取所有符合条件的定时推送消息列表
        Map <String, Object> params = new HashMap <String, Object>();
        params.put("startTime", HSHUtil.minuteAfter(HSHUtil.now(), -10));
        params.put("endTime", HSHUtil.minuteAfter(HSHUtil.now(), 10));
        params.put("type", Message.TYPE_TIMING);
        params.put("state", Message.STATE_NOT_SENT);

        List <Message> messageList;
        try {
            messageList = messageService.getMessageListByParams(params);
            if (messageList == null || messageList.size() == 0) {
                return;
            }
            for (Message message : messageList) {
                messageService.pushMessage(message);
            }

        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
