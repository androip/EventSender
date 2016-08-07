package com.domain.event.receiveService.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.domain.event.FuckEvent;
import com.domain.event.annotation.Address;
import com.domain.event.annotation.Comsumerconfiguration;
import com.domain.event.receiveService.handler.FuckHandler;
/**
 * @ClassName: FuckComsumerListener
 * @Description: 消费应用的监听器，监听和自己相关的事件
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
@Component
@Comsumerconfiguration(topic = "topic1",tag = "tag1")
public class FuckComsumerListener implements MessageListenerConcurrently{

	private static final String Topic = "topic1";
	private static final String Tag = "tag1";
	private static final String key = "1";
	
	
	private FuckHandler handler = new FuckHandler();
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		//接收消息个数msgs.size()
		System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
		MessageExt msg = msgs.get(0);
		if (msg.getTopic().equals(Topic)) {
			if (msg.getTags() != null && msg.getTags().equals(Tag)) {
				String jsonstr = new String(msg.getBody());
				System.out.println(jsonstr);
				FuckEvent event = JSON.parseObject(jsonstr,FuckEvent.class);
				handler.onFuckEvent(event);
			}
		} 
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
