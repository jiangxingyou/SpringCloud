package com.jxy8866.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//RestController也会被扫到啊，Component的意思其实是如果不是三层，dao，service，controller，才用这个注解
@Component
@EnableBinding(Sink.class)
public class RecevieMessageListenerService {
    @Value("${server.port}")
    private String serverport;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号， ----> 接收到的消息："+message.getPayload()+"\n port:"+serverport);
    }
}
