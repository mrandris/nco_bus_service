package com.sda.nco_bus_service.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bus")
    private Integer idBus;

    @Column(name = "bus_name")
    private String busName;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Stop> stopList;

    public Integer getIdBus() {
        return idBus;
    }

    public void setIdBus(Integer idBus) {
        this.idBus = idBus;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public List<Stop> getStopList() {
        return stopList;
    }

    public void setStopList(List<Stop> stopList) {
        this.stopList = stopList;
    }
}
