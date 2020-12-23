package com.bs.it.book.service.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 指定连接某个MQ集群
 */
@Service
//测试rocketmq开启下面注释
//@RocketMQMessageListener(nameServer = "127.0.0.1:9877", topic = "test-topic-3", consumerGroup = "my-consumer_test-topic-3")
public class MyConsumer3 implements RocketMQListener<String> {
    public void onMessage(String message) {
    	System.out.println("test-topic-3 received message: " + message);
    }
}
