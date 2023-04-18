package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dto.TicketDTO;
import com.ftb.ticketmanagementservice.dto.TicketResponseDTO;
import com.ftb.ticketmanagementservice.models.Ticket;

import java.util.Map;
import java.util.UUID;

public interface TicketService {

    Map<String, UUID> createTicket(TicketDTO dto);

    TicketResponseDTO getTicketInfoById(String id);
}
