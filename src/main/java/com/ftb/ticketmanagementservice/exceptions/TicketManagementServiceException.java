package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class TicketManagementServiceException extends RuntimeException {

    private Code code;
    private String message;
    private HttpStatus httpStatus;
}
