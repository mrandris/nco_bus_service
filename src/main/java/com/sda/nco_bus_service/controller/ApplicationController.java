package com.sda.nco_bus_service.controller;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.model.Company;
import com.sda.nco_bus_service.service.BusService;
import com.sda.nco_bus_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    private BusService busService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/index")
    public String findAllRoutes(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
        return "index";
    }
}
