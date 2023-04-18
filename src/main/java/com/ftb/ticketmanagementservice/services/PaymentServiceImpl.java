package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dto.PaymentDTO;
import com.ftb.ticketmanagementservice.dto.PaymentResponseDTO;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.models.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate;

    private final ModelMapper mapper;

    @Override
    public PaymentResponseDTO createPayment(Ticket ticket, BusRoute busRoute) {
        HttpEntity<PaymentDTO> httpEntity = new HttpEntity<>(convertToDto(ticket, busRoute));

        ResponseEntity<PaymentResponseDTO> exchange = restTemplate.exchange("/payments",
                HttpMethod.POST, httpEntity, PaymentResponseDTO.class);

        return exchange.getBody();
    }

    @Override
    public PaymentResponseDTO updatePaymentStatus(String id) {
        return null;
    }

    private PaymentDTO convertToDto(Ticket ticket, BusRoute busRoute) {
        PaymentDTO dto = mapper.map(ticket, PaymentDTO.class);
        mapper.map(busRoute, dto);
        return dto;
    }
}

