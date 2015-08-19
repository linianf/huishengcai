package com.hsh.sms;


import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmsServer {
	
	static Logger log = LoggerFactory.getLogger(SmsServer.class);
	
	public static void main(String[] args){
		System.out.println("短信发送服务启动。。。。。。");
		DOMConfigurator.configure(SmsServer.class.getResource("/log4j.xml"));
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-sms.xml"); 
		SmsSender sender = (SmsSender)ctx.getBean("smsSender");
		sender.emayRegist();
		sender.sendSmsFromQueue();
	}

}
