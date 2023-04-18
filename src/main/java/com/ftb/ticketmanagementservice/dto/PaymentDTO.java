package com.ftb.ticketmanagementservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {

    private String payersName;

    private BigDecimal amount;
}
