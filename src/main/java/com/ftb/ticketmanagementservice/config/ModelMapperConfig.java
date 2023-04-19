package com.ftb.ticketmanagementservice.config;

import com.ftb.ticketmanagementservice.dto.PaymentDTO;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.models.Ticket;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Ticket.class, PaymentDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getOwnerName(), PaymentDTO::setPayersName));
        modelMapper.typeMap(BusRoute.class, PaymentDTO.class)
                .addMappings(mapper -> mapper.map(src -> src.getCost(), PaymentDTO::setAmount));

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
