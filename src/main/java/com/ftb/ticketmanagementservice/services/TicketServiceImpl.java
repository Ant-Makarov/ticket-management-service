package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dao.TicketDAO;
import com.ftb.ticketmanagementservice.dto.PaymentResponseDTO;
import com.ftb.ticketmanagementservice.dto.TicketDTO;
import com.ftb.ticketmanagementservice.dto.TicketResponseDTO;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.models.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    private final BusRouteService busRouteService;

    private final PaymentService paymentService;

    private final ModelMapper mapper;

    private final Clock clock;


    @Override
    @Transactional
    public Map<String, UUID> createTicket(TicketDTO dto) {
        Ticket ticket = prepareForSave(dto);

        return Map.of("id",ticketDAO.save(ticket));
    }

    @Override
    @Transactional
    public TicketResponseDTO getTicketInfoById(String id) {
        return ticketDAO.findById(id);
    }

    private Ticket prepareForSave(TicketDTO dto) {
        Ticket ticket = convertToTicket(dto);
        BusRoute busRoute = busRouteService.reduceSeatsAvailable(ticket.getRouteId());
        PaymentResponseDTO paymentDTO = paymentService.createPayment(ticket, busRoute);

        return fillTicketEntity(ticket, paymentDTO);
    }

    private Ticket convertToTicket(TicketDTO dto) {
        return mapper.map(dto, Ticket.class);
    }

    private Ticket fillTicketEntity(Ticket ticket, PaymentResponseDTO dto) {
        return ticket.toBuilder()
                .paymentId(dto.getPaymentId())
                .paymentStatus(dto.getStatus())
                .createdAt(LocalDateTime.now(clock))
                .build();
    }
}
