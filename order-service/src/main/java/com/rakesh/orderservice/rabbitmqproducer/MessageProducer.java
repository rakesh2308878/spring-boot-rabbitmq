package com.rakesh.orderservice.rabbitmqproducer;

import com.rakesh.orderservice.constant.AppConstant;
import com.rakesh.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // send an order event to order queue
    public void sendMessageToOrderQueue(OrderEvent event) {
        LOGGER.info(String.format("Order event sent to order queue => %s", event.toString()));
        rabbitTemplate.convertAndSend(AppConstant.EXCHANGE_NAME, AppConstant.ORDER_ROUTING_KEY, event);
    }

    // send an order event to email queue
    public void sendMessageToNotificationQueue(OrderEvent event) {
        LOGGER.info(String.format("Order event sent to notification queue => %s", event.toString()));
        rabbitTemplate.convertAndSend(AppConstant.EXCHANGE_NAME, AppConstant.NOTIFICATION_ROUTING_KEY, event);
    }

}
