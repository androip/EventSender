package com.event.test.receive;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.domain.event.receiveService.Dispatcher;
import com.domain.event.receiveService.Receiver;
import com.domain.event.receiveService.listener.FuckComsumerListener;


public class ReceiveEventTest {
	public static void main(String[] args) throws MQClientException, InterruptedException {
		System.out.println("Comsumer prosess");
		MessageListenerConcurrently listenser = new FuckComsumerListener();
		Recvive(listenser);
        System.out.println(".");
    }
	
	@SuppressWarnings("resource")
	public static void Recvive(MessageListenerConcurrently listener) throws BeansException, MQClientException{
    	new AnnotationConfigApplicationContext(Receiver.class).getBean(Receiver.class).receive(listener);
	}
	
	
}
