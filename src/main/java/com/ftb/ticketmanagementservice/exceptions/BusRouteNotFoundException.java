package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import org.springframework.http.HttpStatus;

public class BusRouteNotFoundException extends TicketManagementServiceException {
    public BusRouteNotFoundException(Code code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }
}
