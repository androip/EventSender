package com.domain.event.sendService;

import java.io.Serializable;


public class Addr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5939861628531726270L;
	private String instanceName;
	private String producerGroup;
	private String topic;
	private String tag;
	private String key;
	public Addr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Addr(String instanceName, String producerGroup, String topic, String tag, String key) {
		super();
		this.instanceName = instanceName;
		this.producerGroup = producerGroup;
		this.topic = topic;
		this.tag = tag;
		this.key = key;
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
	@Override
	public String toString() {
		return "Addr [instanceName=" + instanceName + ", producerGroup=" + producerGroup + ", topic=" + topic + ", tag="
				+ tag + ", key=" + key + "]";
	}

	
	
}
