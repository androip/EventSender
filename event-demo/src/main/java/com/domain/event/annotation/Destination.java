package com.domain.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Destination
 * @Description: �������¼�������¼���Ϣ��topic��tag��key
 * @author JP
 * @since v1.0.0
 * @date 2016��7��29��
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
