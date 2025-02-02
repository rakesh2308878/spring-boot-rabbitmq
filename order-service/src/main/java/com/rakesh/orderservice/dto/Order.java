package com.rakesh.orderservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Order {
    private String orderId;
    private String name;
    private int qty;
    private double price;
}
