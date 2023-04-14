package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dao.TicketDAO;
import com.ftb.ticketmanagementservice.dto.PaymentResponseDTO;
import com.ftb.ticketmanagementservice.dto.TicketDTO;
import com.ftb.ticketmanagementservice.models.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    private final ModelMapper mapper;

    private final Clock clock;


    @Override
    @Transactional
    public UUID createTicket(TicketDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public Ticket getTicketInfoById(String id) {
        return ticketDAO.findById(id);
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
