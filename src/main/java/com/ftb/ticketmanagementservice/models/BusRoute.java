package com.ftb.ticketmanagementservice.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class BusRoute {

    private Long id;
    private String fromDeparture;
    private String toDeparture;
    private LocalDateTime departureTime;
    private BigDecimal cost;
    private Integer seatsAvailable;
}
