package com.courier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Packet {
    private Integer packetId;
    private Double weight;
    private String size;
    private String packetType;
    private LocalDate sendDate;
    private Integer statusId;
    private BigDecimal price;
    private Boolean fragile;
    private String deliveryType;
    private Integer recieverId;
    private Integer senderId;
    private Integer registererId;
    private Integer postmanId;
} 