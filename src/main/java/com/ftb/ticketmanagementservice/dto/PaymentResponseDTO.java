package com.ftb.ticketmanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PaymentResponseDTO {

    @JsonProperty(value = "id")
    private UUID paymentId;
    private PaymentStatus status;
}
