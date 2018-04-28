package com.demo.example.producer.services;

import com.demo.example.producer.models.Picture;
import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mock.api.url}")
    private String uri;

    @Value("${mock.api.tolerance}")
    private Integer tolerance;

    @Value("${mock.api.search-range}")
    private Integer searchRange;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;


    @PostConstruct
    public void onStartup() {
        searchForChanges();
    }


    @Scheduled(fixedRate = 10000)
    public void searchForChanges() {
        int current = 0;
        List<Picture> pictures = new ArrayList<>();
        for (int i = 1; i < searchRange; i++) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                Picture result = restTemplate.getForObject(uri + i, Picture.class);
                pictures.add(result);
            } catch (Exception e) {
                current++;
                if (current == tolerance) {
                    break;
                }
            }
        }
        String result = new Gson().toJson(pictures);
        amqpTemplate.convertAndSend(exchange, routingKey, result);
    }
}
