package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.models.BusRoute;

public interface BusRouteService {

    BusRoute findById(Long id);

    BusRoute reduceSeatsAvailable(Long id);
}
