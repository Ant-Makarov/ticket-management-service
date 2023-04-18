package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dto.PaymentDTO;
import com.ftb.ticketmanagementservice.dto.PaymentResponseDTO;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.models.Ticket;

public interface PaymentService {

    PaymentResponseDTO createPayment(Ticket ticket, BusRoute busRoute);

    PaymentResponseDTO updatePaymentStatus(String id);
}
