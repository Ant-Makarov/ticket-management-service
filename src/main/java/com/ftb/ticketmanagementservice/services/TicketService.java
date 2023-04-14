package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dto.TicketDTO;
import com.ftb.ticketmanagementservice.models.Ticket;

import java.util.UUID;

public interface TicketService {

    UUID createTicket(TicketDTO dto);

    Ticket getTicketInfoById(String id);
}
