package com.domain.event;

import java.io.Serializable;

import com.domain.event.sendService.Addr;


public interface IDomainEvent extends Serializable{
	void setAddr(Addr adr);
	Addr getAddr();
}
