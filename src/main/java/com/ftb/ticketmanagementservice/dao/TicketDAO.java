package com.ftb.ticketmanagementservice.dao;

import com.ftb.ticketmanagementservice.dto.TicketResponseDTO;
import com.ftb.ticketmanagementservice.exceptions.TicketNotFoundException;
import com.ftb.ticketmanagementservice.models.Ticket;
import com.ftb.ticketmanagementservice.util.Code;
import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class TicketDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Ticket> mapper;
    private final RowMapper<TicketResponseDTO> dtoRowMapper;

    public UUID save(Ticket ticket) {
        String sql = " insert into task.ticket(owner_name, route_id, payment_id, "
                + " payment_status, created_at) values(:ownerName, :routeId,  "
                + " :paymentId, :paymentStatus, :createdAt)                   ";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ownerName", ticket.getOwnerName());
        params.addValue("routeId", ticket.getRouteId());
        params.addValue("paymentId", ticket.getPaymentId());
        params.addValue("paymentStatus", ticket.getPaymentStatus().name());
        params.addValue("createdAt", ticket.getCreatedAt());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();

        return (UUID) keys.get("ticket_id");

    }

    public TicketResponseDTO findById(String id) {
        UUID uuid = UUID.fromString(id);
        String sql = " select t.ticket_id, t.payment_status, br.from_departure,        "
                + " br.to_departure, br.departure_time, br.route_cost               "
                + " from task.ticket t join task.bus_route br on t.route_id = br.id "
                + " where t.ticket_id = :id                                         ";

        List<TicketResponseDTO> tickets = jdbcTemplate.query(sql, Map.of("id", uuid), dtoRowMapper);
        if (tickets.isEmpty()) {
            throw new TicketNotFoundException(Code.REQUEST_VALIDATION_ERROR,
                    String.format("Ticket with id %s not found", uuid),
                    HttpStatus.BAD_REQUEST);
        }
        return tickets.get(0);
    }

    public Stream<Ticket> findByNewPaymentStatus() {
        String sql = "select ticket_id, route_id, payment_id from task.ticket where payment_status = :paymentStatus";

        return jdbcTemplate.queryForStream(sql, Map.of("paymentStatus", PaymentStatus.NEW.name()), mapper);
    }

    public void updateTicketInfo(Ticket ticket) {
        String sql = " update task.ticket set payment_status = :paymentStatus, "
                + " updated_at = :updatedAt where ticket_id = :id           ";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("paymentStatus", ticket.getPaymentStatus().name());
        params.addValue("updatedAt", ticket.getUpdatedAt());
        params.addValue("id", ticket.getId());

        jdbcTemplate.update(sql, params);
    }
}
