package com.sda.nco_bus_service.service;

import com.sda.nco_bus_service.model.Stop;
import com.sda.nco_bus_service.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {
    @Autowired
    private StopRepository stopRepository;

    public List<Stop> findAllStops() {
        return stopRepository.findAll();
    }

    public Stop save(Stop stop) {
        return stopRepository.save(stop);
    }
}
