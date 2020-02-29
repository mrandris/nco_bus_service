package com.sda.nco_bus_service.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comp")
    private Integer idCompany;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @Column(name = "max_users")
    private Integer maxUsers;
//    @Column(name = "logo")
//    private File logo;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Stop> stopList;

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }

    public List<Stop> getStopList() {
        return stopList;
    }

    public void setStopList(List<Stop> stopList) {
        this.stopList = stopList;
    }

    //    public File getLogo() {
//        return logo;
//    }
//
//    public void setLogo(File logo) {
//        this.logo = logo;
//    }
}
