package com.self.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 向消息发送者发送应答消息
 * @author lixiao
 *
 */
@Component
public class ReplyTo {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(final String consumerMsg, Message produerMessage) throws JMSException{
		jmsTemplate.send(produerMessage.getJMSReplyTo(), new MessageCreator(){
			public Message createMessage(Session session) throws JMSException{
				Message msg = session.createTextMessage("消费者[给生产者]:"+consumerMsg+"的应答");
				return msg;
			}
		});
	}
}
