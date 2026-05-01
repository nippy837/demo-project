package com.nippy.demo.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * MQ 消费者：同一进程里与生产者并存。
 * topic：与发送时使用的 Topic 名称必须完全一致。
 * consumerGroup：消费组标识；同一 Topic、同一 group 的多个实例会一起分摊消息（集群模式）。
 */
@Service
@RocketMQMessageListener(
        topic = "memo-topic",
        consumerGroup = "demo-memo-consumer"
)
public class MemoMqConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {
        // 先只打日志，确认能收到即可
        System.out.println("========== [MQ 消费者] 收到消息: " + message);
    }
}
