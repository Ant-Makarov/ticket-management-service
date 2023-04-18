package com.ftb.ticketmanagementservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse implements Response {

    private Error error;
}
