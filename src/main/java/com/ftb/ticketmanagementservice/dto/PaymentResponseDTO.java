package com.ftb.ticketmanagementservice.dto;

import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {

    private UUID paymentId;
    private PaymentStatus status;
}
