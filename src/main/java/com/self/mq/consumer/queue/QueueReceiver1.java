package com.self.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 队列消息监听器
 * @author lixiao
 *
 */
@Component
public class QueueReceiver1 implements MessageListener{

	@Autowired
	private ReplyTo replyTo;
	
	public void onMessage(Message message){
		try{
			String textMsg = ((TextMessage)message).getText();
			System.out.println("【队列监听器1】监听到生产者消息:"+textMsg);
			//业务
			replyTo.send(textMsg, message);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}
}
