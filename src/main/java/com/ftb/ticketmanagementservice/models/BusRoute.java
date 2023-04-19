package com.ftb.ticketmanagementservice.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusRoute {

    private Long id;
    private String fromDeparture;
    private String toDeparture;
    private LocalDateTime departureTime;
    private BigDecimal cost;
    private Integer seatsAvailable;
}
