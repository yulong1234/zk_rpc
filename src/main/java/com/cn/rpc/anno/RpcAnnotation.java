package com.cn.rpc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    /**
     * 对外发布的服务的接口地址
     * @return
     */
    Class<?> value();

    /**
     * 版本 这个版本号的作用在本次Demo中没有体现出来，不过其实使用也是很简单的，
     *  可以将版本号与ZK node地址中的serviceName拼接或者绑定起来，
     * 然后根据版本号+serviceName获取相应的服务调用地址。那么客户端在发现服务的时候也要传入相应的版本进去。
     * @return
     */
    String version() default "";

}
