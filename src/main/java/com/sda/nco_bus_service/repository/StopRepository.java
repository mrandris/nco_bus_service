package com.sda.nco_bus_service.repository;

import com.sda.nco_bus_service.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, Integer> {
}
