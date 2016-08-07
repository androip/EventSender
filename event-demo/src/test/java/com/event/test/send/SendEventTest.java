package com.event.test.send;


import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.domain.event.FuckEvent;
import com.domain.event.IDomainEvent;
import com.domain.event.annotation.Destination;
import com.domain.event.sendService.Addr;
import com.domain.event.sendService.Deliver;

public class SendEventTest {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws MQClientException, InterruptedException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, InstantiationException {
    	FuckEvent event = new FuckEvent(" ",0);
    	SendEvent(event);
        System.out.println("Done.");
    }
    
    @SuppressWarnings("resource")
	public static void SendEvent(IDomainEvent event) throws BeansException, MQClientException, InterruptedException{
    	Field[] filds = event.getClass().getFields();
    	for(Field f : filds){
    		if(f.getType().getSimpleName().equals("Addr")){
    			Destination des = f.getAnnotation(Destination.class);
    			if(des != null){
    				Addr adr = new Addr(des.instanceName(),des.group(),des.topic(),des.tag(),des.key());
    				event.setAddr(adr);
    			}
    		}
    	}
    	new AnnotationConfigApplicationContext(Deliver.class).getBean(Deliver.class).send(event);
    }

}
