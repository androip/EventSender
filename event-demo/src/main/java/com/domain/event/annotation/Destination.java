package com.domain.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Destination
 * @Description: 作用于事件，标记事件消息的topic、tag、key
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Destination {

	public String group() default "";
	public String instanceName () default "";
	public String topic() default "";
	public String tag() default "";
	public String key() default "";
}
