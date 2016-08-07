package com.domain.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;

/**
 * @ClassName: Comsumerconfiguration
 * @Description: 作用于事件监听器，标记监听器监听与topic、tag、key对应的消息
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comsumerconfiguration {
	public String topic() default "";
	public String tag() default "";
	public String key() default "";
}
