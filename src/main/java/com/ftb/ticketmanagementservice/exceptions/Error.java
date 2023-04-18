package com.ftb.ticketmanagementservice.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ftb.ticketmanagementservice.util.Code;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Code code;
    private String userMessage;
}