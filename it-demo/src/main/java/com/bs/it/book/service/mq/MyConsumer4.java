package com.bs.it.book.service.mq;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 顺序消息消费失败
 */
@Service
//测试rocketmq开启下面注释
//@RocketMQMessageListener(topic = "test-topic-4", consumerGroup = "my-consumer_test-topic-4",
//    consumeMode = ConsumeMode.ORDERLY)
public class MyConsumer4 implements RocketMQListener<String> {
    public void onMessage(String message) {
    	System.out.println("test-topic-4 received message: " + message);
        int a = 1 / 0;
    }
}