package com.demo.example.consumer.rabbitMq;

import com.demo.example.consumer.models.Picture;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    private final Logger log = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(String pictures) {
        Picture[] receivedPictures = new Gson().fromJson(pictures, Picture[].class);
        log.info("I received {}", receivedPictures[0]);
        messagingTemplate.convertAndSend("/topic/reply/", receivedPictures);
    }
}
