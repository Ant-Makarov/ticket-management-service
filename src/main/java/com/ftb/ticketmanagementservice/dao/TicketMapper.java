package com.ftb.ticketmanagementservice.dao;

import com.ftb.ticketmanagementservice.models.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class TicketMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ticket.builder().id(rs.getObject("ticket_id", UUID.class))
                .routeId(rs.getLong("route_id"))
                .paymentId(rs.getObject("payment_id", UUID.class))
                .build();
    }
}
