package com.ftb.ticketmanagementservice.dao;

import com.ftb.ticketmanagementservice.exceptions.BusRouteNotFoundException;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.util.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BusRouteDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<BusRoute> mapper;

    public BusRoute findById(Long id) {
        String sql = " select id, route_cost, seats_avaialble from task.bus_route where id = :id";

        List<BusRoute> routes = jdbcTemplate.query(sql, Map.of("id", id), mapper);
        if (routes.isEmpty()) {
            throw new BusRouteNotFoundException(Code.REQUEST_VALIDATION_ERROR,
                    String.format("Bus  route with id %d not found", id), HttpStatus.BAD_REQUEST);
        }

        return routes.get(0);
    }

    public void update(BusRoute busRoute) {
        String sql = " update task.bus_route set seats_available = :seatsAvailable where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("seatsAvailable", busRoute.getSeatsAvailable());
        params.addValue("id", busRoute.getId());

        jdbcTemplate.update(sql, params);
    }
}
