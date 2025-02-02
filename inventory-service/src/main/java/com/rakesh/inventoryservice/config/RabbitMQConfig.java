package com.rakesh.inventoryservice.config;

import com.rakesh.inventoryservice.constant.AppConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // spring bean for exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(AppConstant.EXCHANGE_NAME);
    }

    // spring bean for queue
    @Bean
    public Queue queue() {
        return new Queue(AppConstant.INVENTORY_QUEUE_NAME, false);
    }

    // spring bean for binding between exchange and queue using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(AppConstant.INVENTORY_ROUTING_KEY);
    }

    // Configuration for Json message
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
