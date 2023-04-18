package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import org.springframework.http.HttpStatus;

public class PaymentNotFoundException extends TicketManagementServiceException {
    public PaymentNotFoundException(Code code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }
}
