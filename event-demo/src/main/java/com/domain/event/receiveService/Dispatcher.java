package com.domain.event.receiveService;

import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

	private String instanceName;
	private String producerGroup;
	private String topic;
	private String tag;
	private String key;
	public Dispatcher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dispatcher(String instanceName, String producerGroup, String topic, String tag, String key) {
		super();
		this.instanceName = instanceName;
		this.producerGroup = producerGroup;
		this.topic = topic;
		this.tag = tag;
		this.key = key;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getProducerGroup() {
		return producerGroup;
	}
	public void setProducerGroup(String producerGroup) {
		this.producerGroup = producerGroup;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
