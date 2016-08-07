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
		 * Producer������ʹ��֮ǰ����Ҫ����start��ʼ������ʼ��һ�μ���<br>
		 * ע�⣺�мǲ�������ÿ�η�����Ϣʱ��������start����
		 */
		producer.start();
		/**
		 * ������δ������һ��Producer������Է��Ͷ��topic�����tag����Ϣ��
		 * ע�⣺send������ͬ�����ã�ֻҪ�����쳣�ͱ�ʶ�ɹ������Ƿ��ͳɹ�Ҳ�ɻ��ж���״̬��<br>
		 * ������Ϣд��Master�ɹ�������Slave���ɹ������������Ϣ���ڳɹ������Ƕ��ڸ���Ӧ���������Ϣ�ɿ���Ҫ�󼫸ߣ�<br>
		 * ��Ҫ������������������⣬��Ϣ���ܻ���ڷ���ʧ�ܵ������ʧ��������Ӧ��������
		 */
		for (int i = 0; i < 1; i++) {
			try {
				Message msg = new Message(event.getAddr().getTopic(),// topic
						event.getAddr().getTag(),// tag
						event.getAddr().getKey(),// key��Ϣ�ؼ��ʣ����Key��KEY_SEPARATOR��������ѯ��Ϣʹ�ã�
						JSON.toJSONString(event).getBytes());// body
				SendResult sendResult = producer.send(msg);
				System.out.println(sendResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
			TimeUnit.MILLISECONDS.sleep(1000);
		}

		/**
		 * Ӧ���˳�ʱ��Ҫ����shutdown��������Դ���ر��������ӣ���MetaQ��������ע���Լ�
		 * ע�⣺���ǽ���Ӧ����JBOSS��Tomcat���������˳����������shutdown����
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
