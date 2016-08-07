package com.domain.event.sendService;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.domain.event.IDomainEvent;

@Component
@Configuration
@ComponentScan("com.domain")
@PropertySource("classpath:/config.properties")
public class Deliver {

	@Value(value = "Producer")
	private String instanceName;
	@Value(value = "ProducerGroupName")
	private String producerGroup;
    private @Value("${rockmq.addr}") String rockmqaddr;
    private final DefaultMQProducer producer = new DefaultMQProducer(); 

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DefaultMQProducer mqProducerFactory(){
		producer.setNamesrvAddr(rockmqaddr);
		producer.setInstanceName(instanceName);
		producer.setProducerGroup(producerGroup);
		return producer;
    }

	public void send(IDomainEvent event) throws MQClientException,InterruptedException {
		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		producer.start();
		/**
		 * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
		 * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
		 * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
		 * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
		 */
		for (int i = 0; i < 1; i++) {
			try {
				Message msg = new Message(event.getAddr().getTopic(),// topic
						event.getAddr().getTag(),// tag
						event.getAddr().getKey(),// key消息关键词，多个Key用KEY_SEPARATOR隔开（查询消息使用）
						JSON.toJSONString(event).getBytes());// body
				SendResult sendResult = producer.send(msg);
				System.out.println(sendResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}

		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
		 */
		// producer.shutdown();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				producer.shutdown();
			}
		}));
		System.exit(0);
	}
	
    
    
}
