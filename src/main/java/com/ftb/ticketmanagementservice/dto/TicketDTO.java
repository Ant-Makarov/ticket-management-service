package com.ftb.ticketmanagementservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketDTO {

    @NotEmpty
    @Size(min = 2, max = 30, message = "Owner's name should be between 2 and 30 characters!")
    private String ownerName;

    @NotNull(message = "Route id shouldn't be null!")
    private Long routeId;
}
