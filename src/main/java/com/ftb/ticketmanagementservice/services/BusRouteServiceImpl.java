package com.ftb.ticketmanagementservice.services;

import com.ftb.ticketmanagementservice.dao.BusRouteDAO;
import com.ftb.ticketmanagementservice.exceptions.NoSeatsAvailableOnRouteException;
import com.ftb.ticketmanagementservice.models.BusRoute;
import com.ftb.ticketmanagementservice.util.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteDAO dao;
    @Override
    public BusRoute findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public BusRoute reduceSeatsAvailable(Long id) {
        BusRoute busRoute = findById(id);
        if (busRoute.getSeatsAvailable() == 0) {
            throw new NoSeatsAvailableOnRouteException(Code.REQUEST_VALIDATION_ERROR,
                    "No seats available for ".concat(busRoute.toString()), HttpStatus.BAD_REQUEST);
        }

        busRoute.setSeatsAvailable(busRoute.getSeatsAvailable() - 1);
        dao.update(busRoute);

        return busRoute;
    }
}
