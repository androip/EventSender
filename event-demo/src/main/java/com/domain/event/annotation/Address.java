package com.domain.event.annotation;

/**
 * @ClassName: Address
 * @Description: 标记事件的发送目的地
 * @author JP
 * @since v1.0.0
 * @date 2016年7月29日
 */
public @interface Address {
	public String topic() default "";
	public String tag() default "";
	public String key() default "";
}
