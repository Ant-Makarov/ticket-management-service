package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import org.springframework.http.HttpStatus;

public class InvalidRequestDataException extends TicketManagementServiceException {
    public InvalidRequestDataException(Code code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }
}
