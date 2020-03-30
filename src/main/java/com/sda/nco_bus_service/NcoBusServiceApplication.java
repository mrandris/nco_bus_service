package com.sda.nco_bus_service;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.repository.BusRepository;
import com.sda.nco_bus_service.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NcoBusServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(NcoBusServiceApplication.class, args);
	}
}
