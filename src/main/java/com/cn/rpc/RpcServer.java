package com.cn.rpc;

import com.cn.rpc.anno.RpcAnnotation;
import com.cn.rpc.zk.IRegisterCenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 用于发布一个远程服务，服务端要将服务发布到注册中心
 */
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    private IRegisterCenter registerCenter; //注册中心
    private String serviceAddress; //服务发布地址

    // 存放服务名称和服务对象之间的关系
    Map<String,Object> handlerMap=new HashMap<String,Object>();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services){
        for(Object service:services){
            System.out.println(service);
            RpcAnnotation annotation=service.getClass().getAnnotation(RpcAnnotation.class);
            System.out.println("bind--annotation:"+annotation);
            String serviceName=annotation.value().getName();
            String version=annotation.version();
            if(version!=null&&!version.equals("")){
                serviceName=serviceName+"-"+version;
            }
            handlerMap.put(serviceName,service);//绑定服务接口名称对应的服务
        }
    }
    //发布方法
    public void publisher(){
        ServerSocket serverSocket=null;
        try{
            String[] addrs=serviceAddress.split(":");
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));  //启动一个服务监听

            for(String interfaceName:handlerMap.keySet()){
                registerCenter.register(interfaceName,serviceAddress);
                System.out.println("注册服务成功："+interfaceName+"->"+serviceAddress);
            }

            while(true){ //循环监听
                Socket socket=serverSocket.accept(); //监听服务
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
