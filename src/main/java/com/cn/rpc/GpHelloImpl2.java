package com.cn.rpc;


import com.cn.rpc.anno.RpcAnnotation;

@RpcAnnotation(value = IGpHello.class)
public class GpHelloImpl2 implements IGpHello{

    public String sayHello(String msg) {
        return "I'm 8081 node ："+msg;
    }
}
