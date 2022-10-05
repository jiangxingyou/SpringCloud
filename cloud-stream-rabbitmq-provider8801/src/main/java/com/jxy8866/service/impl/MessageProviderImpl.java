package com.jxy8866.service.impl;

import com.jxy8866.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
//包不要引入错误了；
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)//一个消息的发送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;//这个名字必须是这个；且是一个消息管道

    @Override
    public String send() {
        //因为构建了message对象  把它传给管道Source，然后再给MQ的，不是用返回值
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*******serial:"+serial);
        return serial;
    }
}
