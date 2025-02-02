package com.rakesh.inventoryservice.rabbitmqconsumer;

import com.rakesh.inventoryservice.constant.AppConstant;
import com.rakesh.inventoryservice.dto.OrderEvent;
import com.rakesh.inventoryservice.rabbitmqproducer.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    MessageProducer messageProducer;

    @RabbitListener(queues = AppConstant.ORDER_QUEUE_NAME)
    public void listenMessageFromOrderQueue(OrderEvent event) {
        LOGGER.info(String.format("Order event received in inventory service => %s", event.toString()));
        messageProducer.sendMessageToNotificationQueue(event);
    }

}
