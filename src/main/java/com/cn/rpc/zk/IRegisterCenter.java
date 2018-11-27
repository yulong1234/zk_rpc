package com.cn.rpc.zk;

/**
 * 注册中心顶层接口
 */
public interface IRegisterCenter {

    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);

}
