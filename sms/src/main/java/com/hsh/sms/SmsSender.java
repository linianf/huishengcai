package com.hsh.sms;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hsh.sms.emay.Client;
import com.hsh.sms.emay.SingletonClient;
import com.hsh.util.Constants;
import com.hsh.util.RedisUtil;

/**
 * 短信发送器
 * 负责从redis队列里面获取待发送短信，并发送
 * @author linianf
 *
 */
@Service
public class SmsSender {
    
	private static Logger log = LoggerFactory.getLogger(SmsSender.class);
	
	@Value("${hospital-softwareSerialNo}")
	private String hospitalEmaySerial;
	@Value("${hospital-emayPass}")
	private String hospitalEmayPass;
	@Value("${hospital-key}")
	private String hospitalEmayKey;
	
	@Value("${hsh-softwareSerialNo}")
	private String hshEmaySerial;
	@Value("${hsh-emayPass}")
	private String hshEmayPass;
	@Value("${hsh-key}")
	private String hshEmayKey;
	
	@Value("${hsc-softwareSerialNo}")
	private String hscEmaySerial;
	@Value("${hsc-emayPass}")
	private String hscEmayPass;
	@Value("${hsc-key}")
	private String hscEmayKey;
	
	@Autowired
	private RedisUtil redisUtil;
	
	public  void  sendSmsFromQueue(){
		while(true){
			try{
				String smsStr = redisUtil.rpop(Constants.SMS_QUEUE);
				if(StringUtils.isEmpty(smsStr)){
					Thread.sleep(1000);
					continue;
				}
				JSONObject obj = (JSONObject)JSON.parse(smsStr);
				JSONArray mobileArray = (JSONArray)obj.get("mobile");
				String[] mobile = new String[mobileArray.size()];
				for(int i=0;i<mobileArray.size();i++){
					mobile[i] = mobileArray.getString(i);
				}
				String content = (String)obj.get("content");
				int busiType = obj.getIntValue("busiType");
				System.out.println(busiType);
				Client client = getClient(busiType);
				System.out.println(client.getSoftwareSerialNo());
				client.sendSMS(mobile, content, "",5);// 带扩展码
			}catch(Exception e){
				log.error(e.getLocalizedMessage());
			}
		}
	}
	
	public  void  emayRegist(){
		try{
			getClient(1).registEx(hospitalEmayPass);
			getClient(2).registEx(hscEmayPass);
			getClient(3).registEx(hshEmayPass);
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
		}
	}
	
	public  Client getClient(int busiType){
		if(busiType==1)
			return SingletonClient.getHospitalClient(hospitalEmaySerial,hospitalEmayKey);
		if(busiType==2)
			return SingletonClient.getHscClient(hscEmaySerial,hscEmayKey);
		else
			return SingletonClient.getHshClient(hshEmaySerial,hshEmayKey);
	}
}
