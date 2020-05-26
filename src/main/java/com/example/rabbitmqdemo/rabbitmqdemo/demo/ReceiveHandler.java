package com.example.rabbitmqdemo.rabbitmqdemo.demo;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_email", durable = "true"), exchange = @Exchange(value = "topic.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"topic.#.email.#", "emial.*"}
    ))
    public void rece_email(String msg) {
        System.out.println("[邮件服务] received" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue_sms", durable = "true"),
            exchange = @Exchange(
                    value = "topic.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"topic.#.sms.#"}
    ))
    public void rece_sms(String msg) {
        System.out.println("[短信服务] received" + msg);
    }
}
