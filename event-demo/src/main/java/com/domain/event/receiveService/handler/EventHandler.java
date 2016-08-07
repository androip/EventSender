package com.domain.event.receiveService.handler;


import com.domain.event.FuckEvent;

public interface EventHandler {
	void onFuckEvent(FuckEvent event);
}
