package com.domain.event.receiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.domain.event.annotation.Comsumerconfiguration;

/**
 * @ClassName: Receiver
 * @Description: 事件接受。反序列化并解析事件，然后调用Consumer应用处理事件。
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
@Component
@Configuration
@ComponentScan("com.domain")
@PropertySource("classpath:/config.properties")
public class Receiver{

    private @Value("${rockmq.addr}") String rockmqaddr;
	private @Value(value = "Consumer") String instanceName;
	private @Value(value = "ConsumerGroupName") String group;
	private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);
//	private MessageListener msglis;
	@Autowired
	private Dispatcher dispatcher;
	

	@Bean
	public MessageListener listenerResolver(MessageListener lis){
		Comsumerconfiguration an = lis.getClass().getAnnotation(Comsumerconfiguration.class);
		dispatcher.setTopic(an.topic());
		dispatcher.setTag(an.tag());
		dispatcher.setKey(an.key());
		return lis;
	}
	
	@Bean
	public DefaultMQPushConsumer mqConsumerFactory(){
		consumer.setNamesrvAddr(rockmqaddr);
		consumer.setInstanceName(instanceName);
		consumer.setConsumerGroup(group);
		try {
//			consumer.subscribe("fuck", "hjx || TagA || TagC || TagD");
//			consumer.subscribe("TopicTest2", "*");
			consumer.subscribe(dispatcher.getTopic(), dispatcher.getTag());
		} catch (MQClientException e) {
			e.printStackTrace();
		}
		return consumer;
	}
	
	
	
	/**
	 * 当前例子是Consumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端�??<br>
	 * 但是实际Consumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
	 * @throws MQClientException
	 */
	public void receive(MessageListener linstener) throws MQClientException {
		consumer.registerMessageListener(linstener);
		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即�?<br>
		 */
		consumer.start();
		System.out.println("ConsumerStarted...");
	}

}
