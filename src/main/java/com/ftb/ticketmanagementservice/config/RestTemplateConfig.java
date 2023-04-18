package com.ftb.ticketmanagementservice.config;

import com.ftb.ticketmanagementservice.rest.CustomClientHttpRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final CustomClientHttpRequestInterceptor requestInterceptor;

    private final ResponseErrorHandler errorHandler;

    @Value("${payment-service.uri}")
    private String paymentServiceUri;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriTemplateHandler uriTemplateHandler = new DefaultUriBuilderFactory(paymentServiceUri);

        return builder.uriTemplateHandler(uriTemplateHandler)
                      .errorHandler(errorHandler)
                      .additionalInterceptors(requestInterceptor)
                      .build();
    }

}
