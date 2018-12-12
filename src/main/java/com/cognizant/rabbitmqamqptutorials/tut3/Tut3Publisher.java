package com.cognizant.rabbitmqamqptutorials.tut3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
@Profile("tut3-publisher")
final class Tut3Publisher {
    @Autowired
    ConnectionFactory connectionFactory;
    public static  final String  EXCHANGE_NAME="logs2";


    @Scheduled(fixedDelay = 8000, initialDelay = 2000)
    public void send() throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = "Message to exchange";

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");

    }
}
