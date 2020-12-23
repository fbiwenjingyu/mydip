package com.bs.it.book.service.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import com.bs.it.book.vo.OrderExt;

/**
 * 简单接收vo对象的消息
 * @author chh
 *
 */
@Service
//测试rocketmq开启下面注释
//@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class MyConsumer2 implements RocketMQListener<OrderExt> {
    public void onMessage(OrderExt orderExt) {
    	System.out.println("test-topic-2 received orderPaidEvent: " + orderExt);
    }
}
