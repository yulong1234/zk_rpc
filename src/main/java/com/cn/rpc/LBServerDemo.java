package com.cn.rpc;

import com.cn.rpc.zk.IRegisterCenter;
import com.cn.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

public class LBServerDemo {
    public static void main(String[] args) throws IOException {
        IGpHello iGpHello=new GpHelloImpl();
        IRegisterCenter registerCenter=new RegisterCenterImpl();
        RpcServer rpcServer=new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(iGpHello);
        rpcServer.publisher();
        System.in.read();
    }
}
