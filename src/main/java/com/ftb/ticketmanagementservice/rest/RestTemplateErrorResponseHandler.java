package com.ftb.ticketmanagementservice.rest;

import com.ftb.ticketmanagementservice.exceptions.InvalidRequestDataException;
import com.ftb.ticketmanagementservice.exceptions.PaymentNotFoundException;
import com.ftb.ticketmanagementservice.util.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorResponseHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
            throw new InvalidRequestDataException(Code.REQUEST_VALIDATION_ERROR,
                    String.format("Invalid data passed in request body"),
                    HttpStatus.BAD_REQUEST);
        } else if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new PaymentNotFoundException(Code.REQUEST_VALIDATION_ERROR,
                    String.format("Payment with such id not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
}
