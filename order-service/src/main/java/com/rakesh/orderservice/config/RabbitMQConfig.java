package com.rakesh.orderservice.config;

import com.rakesh.orderservice.constant.AppConstant;
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
    public Queue orderQueue() {
        return new Queue(AppConstant.ORDER_QUEUE_NAME, false);
    }

    // spring bean for queue
    @Bean
    public Queue notificationQueue() {
        return new Queue(AppConstant.NOTIFICATION_QUEUE_NAME, false);
    }

    // spring bean for binding between exchange and queue using routing key
    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(exchange()).with(AppConstant.ORDER_ROUTING_KEY);
    }

    // spring bean for binding between exchange and queue using routing key
    @Bean
    public Binding notificationQueueBinding() {
        return BindingBuilder.bind(notificationQueue()).to(exchange()).with(AppConstant.NOTIFICATION_ROUTING_KEY);
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
