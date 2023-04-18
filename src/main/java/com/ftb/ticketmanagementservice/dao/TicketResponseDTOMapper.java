package com.ftb.ticketmanagementservice.dao;

import com.ftb.ticketmanagementservice.dto.TicketResponseDTO;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.util.PaymentStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TicketResponseDTOMapper implements RowMapper<TicketResponseDTO> {

    @Override
    public TicketResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TicketResponseDTO.builder()
                .ticketId(rs.getObject("ticket_id", UUID.class))
                .paymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")))
                .busRouteInfo(BusRoute.builder()
                                      .fromDeparture(rs.getString("from_departure"))
                                      .toDeparture(rs.getString("to_departure"))
                                      .departureTime(rs.getTimestamp("departure_time").toLocalDateTime())
                                      .cost(rs.getBigDecimal("route_cost"))
                                      .build())
                .build();
    }
}
