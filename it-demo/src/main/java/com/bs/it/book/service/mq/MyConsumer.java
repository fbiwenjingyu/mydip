package com.bs.it.book.service.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 简单接收string对象的消息
 * @author chh
 *
 */
@Service
// 测试rocketmq开启下面注释
//@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer implements RocketMQListener<String> {
    public void onMessage(String message) {
        System.out.println("test-topic-1 received message: " + message);
    }
}
