package com.example.rabbitmqdemo.rabbitmqdemo;

import com.example.rabbitmqdemo.rabbitmqdemo.demo.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Send {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsgByTopics() {
        for (int i = 0; i < 5; i++) {
            String message = "恭喜您，注册成功!userid=" + i;

            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "topic.sms.email", message);
            System.out.println("[x] sent" + message);
        }
    }
}
