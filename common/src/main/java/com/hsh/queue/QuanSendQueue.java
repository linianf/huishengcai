package com.hsh.queue;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsh.model.QuanGetRecord;
import com.hsh.service.UserCrowdService;
import com.hsh.util.Constants;
import com.hsh.util.HSHUtil;
import com.hsh.util.RedisUtil;
import com.hsh.vo.SmsVO;

@Service
public class QuanSendQueue {

	private static Logger log = LoggerFactory.getLogger(QuanSendQueue.class);
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private HSHUtil hshUtil;
	
	@Autowired
	private UserCrowdService userCrowdService;
	
	public  void  sendQuanFromQueue(){
		while(true){
			try{
				String quanStr = redisUtil.rpop(Constants.QUAN_QUEUE);
				if(StringUtils.isEmpty(quanStr)){
					Thread.sleep(1000);
					continue;
				}
				JSONObject obj = (JSONObject)JSON.parse(quanStr);
				int amount = obj.getIntValue("amount");
				int userId = obj.getIntValue("userId");
				int crowdId = obj.getIntValue("crowdId");
				int itemId = obj.getIntValue("itemId");
				String mobile = obj.getString("mobile");
				//调用惠生活接口，获得相应金额的卷
				String quanNO = "";
				try{
				   quanNO = hshUtil.getQuanFromHSH(amount);
				}catch(Exception e){
				   log.error("调用hsh quan接口异常",e);	
				   redisUtil.lpush(Constants.QUAN_QUEUE, quanStr);
				   continue;
				}
				QuanGetRecord record = new QuanGetRecord();
				record.setAmount(amount);
				record.setCallDate(new Date());
				record.setQuanNO(quanNO);
				record.setUserId(userId);
				record.setCrowdId(crowdId);
				record.setItemId(itemId);
				record.setMobile(mobile);
				userCrowdService.saveOrUpdate(record);
				//发送兑换卷邮件或者短信
				SmsVO vo = new SmsVO();
				vo.setContent("恭喜您参与的项目成功结束，获得金额为"+(int)(amount/100)+"元的惠生活兑换券，请您牢记卷号："+
				quanNO + " ，注意不要泄露");
				vo.setMobile(new String[]{mobile});
				vo.setBusiType(2);
				redisUtil.lpush(Constants.SMS_QUEUE,JSON.toJSONString(vo));  
			}catch(Exception e){
				log.error(e.getLocalizedMessage());
			}
		}
	}
}
