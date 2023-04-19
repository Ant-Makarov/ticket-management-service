package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class TicketManagementServiceErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(TicketNotCreatedException e) {
        log.error("Ticket hasn't been created: {}", e.getMessage());
        return new ResponseEntity<>(createBody(e), e.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(TicketManagementServiceException e) {
        log.error("Error: {}", e.getMessage());
        return new ResponseEntity<>(createBody(e), e.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Error: {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(new Error(Code.INTERNAL_SERVER_ERROR, e.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse createBody(TicketManagementServiceException e) {
        return new ErrorResponse(Error.builder()
                .code(e.getCode())
                .userMessage(e.getMessage())
                .build());
    }
}
