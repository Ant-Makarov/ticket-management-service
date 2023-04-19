package com.ftb.ticketmanagementservice.dao;

import com.ftb.ticketmanagementservice.models.BusRoute;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BusRouteMapper implements RowMapper<BusRoute> {
    @Override
    public BusRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BusRoute.builder()
                .id(rs.getLong("id"))
                .cost(rs.getBigDecimal("route_cost"))
                .seatsAvailable(rs.getInt("seats_available"))
                .build();
    }
}
