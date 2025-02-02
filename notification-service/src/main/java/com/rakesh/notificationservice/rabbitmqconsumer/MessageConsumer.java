package com.rakesh.notificationservice.rabbitmqconsumer;

import com.rakesh.notificationservice.constant.AppConstant;
import com.rakesh.notificationservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @RabbitListener(queues = AppConstant.ORDER_QUEUE_NAME)
    public void listenMessageFromOrderQueue(OrderEvent event) {
        LOGGER.info(String.format("Order event received in order service => %s", event.toString()));
    }

    @RabbitListener(queues = AppConstant.INVENTORY_QUEUE_NAME)
    public void listenMessageFromInventoryQueue(OrderEvent event) {
        LOGGER.info(String.format("Order event received in inventory and payment service => %s", event.toString()));
    }

}
