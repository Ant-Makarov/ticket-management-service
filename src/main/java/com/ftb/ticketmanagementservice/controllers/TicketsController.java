package com.ftb.ticketmanagementservice.controllers;

import com.ftb.ticketmanagementservice.dto.TicketDTO;
import com.ftb.ticketmanagementservice.dto.TicketResponseDTO;
import com.ftb.ticketmanagementservice.exceptions.TicketNotCreatedException;
import com.ftb.ticketmanagementservice.services.TicketService;
import com.ftb.ticketmanagementservice.util.Code;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static com.ftb.ticketmanagementservice.exceptions.TicketNotCreatedException.createErrorMessage;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<Map<String, UUID>> createTicket(@RequestBody @Valid TicketDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TicketNotCreatedException(Code.REQUEST_VALIDATION_ERROR,
                    createErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ticketService.createTicket(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<TicketResponseDTO> getTicketInfo(String id) {
        return new ResponseEntity<>(ticketService.getTicketInfoById(id), HttpStatus.OK);
    }
}
