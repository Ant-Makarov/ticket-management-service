package com.ftb.ticketmanagementservice.exceptions;

import com.ftb.ticketmanagementservice.util.Code;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class TicketNotCreatedException extends TicketManagementServiceException {
    public TicketNotCreatedException(Code code, String message, HttpStatus httpStatus) {
        super(code, message, httpStatus);
    }

    public static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error -> {
            stringBuilder.append(error.getField())
                    .append("-")
                    .append(error.getDefaultMessage())
                    .append("; ");
        });
        return stringBuilder.toString();
    }
}
