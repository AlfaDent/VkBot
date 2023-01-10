package com.example.vkbotspring;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.VkApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class VkBotSpringApplication {

    public static void main(String[] args) throws VkApiException {
        SpringApplication.run(VkBotSpringApplication.class, args);
        new BotsLongPoll(new VkBot()).run();
    }

}
