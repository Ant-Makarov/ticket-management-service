package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dao.BusRouteDAO;
import com.ftb.ticketmanagementservice.dao.TicketDAO;
import com.ftb.ticketmanagementservice.dto.PaymentResponseDTO;
import com.ftb.ticketmanagementservice.exceptions.PaymentNotFoundException;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.models.Ticket;
import com.ftb.ticketmanagementservice.util.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduledTicketPaymentStatusHandler {

    private final TicketDAO ticketDAO;

    private final BusRouteDAO busRouteDAO;

    private final PaymentService paymentService;
    private final Clock clock;

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    @Transactional
    public void updateTicketPaymentInfo() {
        ticketDAO.findByNewPaymentStatus().forEach(this::handleTicket);
    }

    private void handleTicket(Ticket ticket) throws PaymentNotFoundException {
        PaymentResponseDTO responseDTO = paymentService.updatePaymentStatus(ticket.getPaymentId());
        if (responseDTO.getStatus().equals(PaymentStatus.FAILED)) {
            performPaymentIsFailedLogic(ticket, responseDTO);
        } else if (responseDTO.getStatus().equals(PaymentStatus.DONE)) {
            performPaymentIsDoneLogic(ticket, responseDTO);
        }
    }

    private void performPaymentIsDoneLogic(Ticket ticket, PaymentResponseDTO dto) {
        Ticket updatedTicket = updateTicketEntity(ticket, dto);
        ticketDAO.updateTicketInfo(updatedTicket);
    }

    private void performPaymentIsFailedLogic(Ticket ticket, PaymentResponseDTO dto) {
        Ticket updatedTicket = updateTicketEntity(ticket, dto);
        ticketDAO.updateTicketInfo(updatedTicket);

        BusRoute busRoute = busRouteDAO.findById(ticket.getRouteId());
        busRoute.setSeatsAvailable(busRoute.getSeatsAvailable() + 1);
        busRouteDAO.update(busRoute);
    }

    private Ticket updateTicketEntity(Ticket ticket, PaymentResponseDTO dto) {
        return ticket.toBuilder()
                .paymentStatus(dto.getStatus())
                .updatedAt(LocalDateTime.now(clock))
                .build();
    }
}
