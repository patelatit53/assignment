package com.demo.example.consumer.rabbitMq;

import com.demo.example.consumer.models.Picture;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void receivedMessage(String pictures) {
        Picture[] recivedPictures = new Gson().fromJson(pictures, Picture[].class);
        messagingTemplate.convertAndSend("/topic/reply/",recivedPictures);
    }
}
