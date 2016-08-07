package com.domain.event;

import org.springframework.stereotype.Service;

import com.domain.event.annotation.Destination;
import com.domain.event.sendService.Addr;

@Service()
public class FuckEvent implements IDomainEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2307595757773710573L;

	@Destination(topic="topic1",tag="tag1",key="1")
	public Addr addr;
		
	private String name;
	private Integer age;

	public FuckEvent(){}
	
	public FuckEvent(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Addr getAddr() {
		return addr;
	}
	
	public void setAddr(Addr addr) {
		this.addr = addr;
	}
	
}
