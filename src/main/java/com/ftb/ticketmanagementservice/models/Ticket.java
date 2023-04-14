package com.ftb.ticketmanagementservice.models;

import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class Ticket {

    private UUID id;
    private String ownerName;
    private Long routeId;
    private UUID paymentId;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
