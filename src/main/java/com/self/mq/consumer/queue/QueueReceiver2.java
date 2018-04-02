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
public class QueueReceiver2 implements MessageListener{

	@Autowired
	private ReplyTo replyTo;
	
	public void onMessage(Message arg0) {
		try{
			String textMsg = ((TextMessage)arg0).getText();
			System.out.println("【队列监听器2】监听到生产者消息："+textMsg);
			replyTo.send(textMsg, arg0);
		}catch(JMSException e){
			e.printStackTrace();
		}
	}

	
}
