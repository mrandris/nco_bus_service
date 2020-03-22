package com.sda.nco_bus_service.model;

import javax.persistence.*;

@Entity(name = "stop")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_stop")
    private Integer idStop;

    @ManyToOne
    @JoinColumn(name="id_bus", nullable=false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name="id_comp", nullable=false)
    private Company company;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "id_bus_present")
    private Boolean isPresent;

    public Integer getIdStop() {
        return idStop;
    }

    public void setIdStop(Integer idStop) {
        this.idStop = idStop;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
