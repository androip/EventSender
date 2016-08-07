package com.domain.event.annotation;

/**
 * @ClassName: Address
 * @Description: ����¼��ķ���Ŀ�ĵ�
 * @author JP
 * @since v1.0.0
 * @date 2016��7��29��
 */
public @interface Address {
	public String topic() default "";
	public String tag() default "";
	public String key() default "";
}
