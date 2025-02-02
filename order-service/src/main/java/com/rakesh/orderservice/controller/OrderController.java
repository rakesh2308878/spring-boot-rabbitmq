package com.rakesh.orderservice.controller;

import com.rakesh.orderservice.dto.Order;
import com.rakesh.orderservice.dto.OrderEvent;
import com.rakesh.orderservice.rabbitmqproducer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    MessageProducer messageProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = OrderEvent.builder()
                .status("PENDING").message("order status is in pending state")
                .order(order).build();

        messageProducer.sendMessageToOrderQueue(orderEvent);
        messageProducer.sendMessageToNotificationQueue(orderEvent);

        return "Order sent to the RabbitMQ ..";
    }

}
