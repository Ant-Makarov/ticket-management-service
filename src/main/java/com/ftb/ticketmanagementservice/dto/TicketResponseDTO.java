package com.ftb.ticketmanagementservice.dto;

import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private UUID ticketId;

    private PaymentStatus paymentStatus;

    private BusRoute busRouteInfo;
}
