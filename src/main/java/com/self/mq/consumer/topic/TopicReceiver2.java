package com.self.mq.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self.mq.consumer.queue.ReplyTo;

/**
 * Topic消息监听器
 * @author lixiao
 *
 */
@Component
public class TopicReceiver2 implements MessageListener{

	@Autowired
	private ReplyTo replyto;
	public void onMessage(Message arg0) {
		try {
			String textMsg = ((TextMessage)arg0).getText();
			System.out.println("【topic监听器2】监听到生产者消息:"+textMsg);
			replyto.send(textMsg, arg0);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
