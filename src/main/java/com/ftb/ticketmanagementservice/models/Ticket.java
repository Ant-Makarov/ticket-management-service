package com.ftb.ticketmanagementservice.models;

import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor@AllArgsConstructor
public class Ticket {

    private UUID id;
    private String ownerName;
    private Long routeId;
    private UUID paymentId;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
