package com.sda.nco_bus_service.service;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public List<Bus> findAllBuses() {
        return busRepository.findAll();
    }

    public Bus findById(Integer idBus) {
        return busRepository.findById(idBus).get();
    }

    public void deleteBus(Integer idBus) {
        busRepository.deleteById(idBus);
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public Bus updateBus(Bus bus) {
        return busRepository.save(bus);
    }

}
