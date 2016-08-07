package com.domain.event.receiveService.handler;

import org.springframework.stereotype.Component;

import com.domain.event.FuckEvent;

/**
 * @ClassName: FuckHandler
 * @Description: 事件处理器
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
@Component("fucklis")
public class FuckHandler implements EventHandler{
	public void onFuckEvent(FuckEvent event){
		System.out.println(" I'm fucking "+event.getName()+" whose age is "+event.getAge()+" ,I feel so cool ");
	}
}
