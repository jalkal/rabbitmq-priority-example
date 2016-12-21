package com.jalkal.test;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by user on 21/12/2016.
 */
public class Sender {

    ConnectionFactory cf = new Listener().connectionFactory();
    RabbitTemplate rabbitTemplate = new RabbitTemplate(cf);
    MessageProperties messageProperties = new MessageProperties();

    public static void main(String... args){

        Sender sender = new Sender();

        for(int i=0 ; i<500 ; i++ ){
            sender.send(1);
            sender.send(2);
            sender.send(3);
            sender.send(4);
            sender.send(5);
            sender.send(6);
            sender.send(7);
            sender.send(8);
            sender.send(9);
        }
    }

    private void send(int property){
        messageProperties.setPriority(property);
        Message message = new Message(new String("hello").getBytes() , messageProperties);
        rabbitTemplate.send("spring-boot-exchange", Listener.queueName, message);
    }
}
