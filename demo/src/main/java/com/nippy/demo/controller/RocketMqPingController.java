package com.nippy.demo.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 调试用生产者：通过 HTTP 触发一次发送，方便验证 MQ。
 * 调通后可删，或改为在 MemoServiceImpl 里发送。
 */
@RestController
@RequestMapping("/api/mq")
public class RocketMqPingController {

    private final RocketMQTemplate rocketMQTemplate;


    public RocketMqPingController(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 浏览器或 curl：GET /api/mq/send?text=hello
     * 会往 Topic「memo-topic」发一条字符串，应与上面的消费者 topic 一致。
     */
    @GetMapping("/sent")
    public String send(@RequestParam(defaultValue = "hello-mq")String text ){
        rocketMQTemplate.convertAndSend("memo-topic", text);
        return "已发送：" + text;
    }
}
