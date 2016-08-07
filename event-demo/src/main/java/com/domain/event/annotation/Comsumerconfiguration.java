package com.domain.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Repository;

/**
 * @ClassName: Comsumerconfiguration
 * @Description: �������¼�����������Ǽ�����������topic��tag��key��Ӧ����Ϣ
 * @author JP
 * @since v1.0.0
 * @date 2016��7��29��
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Comsumerconfiguration {
	public String topic() default "";
	public String tag() default "";
	public String key() default "";
}
