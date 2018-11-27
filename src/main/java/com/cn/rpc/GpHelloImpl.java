package com.cn.rpc;

import com.cn.rpc.anno.RpcAnnotation;


@RpcAnnotation(IGpHello.class)
public class GpHelloImpl implements IGpHello{

    public String sayHello(String msg) {
        return "I'm 8080 Node , "+msg;
    }
}
