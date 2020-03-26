package com.sda.nco_bus_service.controller;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.model.Company;
import com.sda.nco_bus_service.model.Stop;
import com.sda.nco_bus_service.service.BusService;
import com.sda.nco_bus_service.service.CompanyService;
import com.sda.nco_bus_service.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/stops")
public class StopController {
    @Autowired
    private StopService stopService;
    @Autowired
    private BusService busService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/findAll")
    public String findAllStops(ModelMap model) {
        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("stopList", stopList);
        return "stopListView";
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.GET)
    public String addStop(ModelMap model) {
        model.addAttribute("stop", new Stop());
        List<Company> companyList = companyService.findAllCompanies();
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
        System.out.println("Working1");

        return "addStop";
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "stop") Stop stop, ModelMap model) {
        System.out.println("Bus: " + stop.getBus().getIdBus() + " Company: " + stop.getCompany().getIdCompany());
        System.out.println("ID: " + stop.getIdStop() + " Rank: " + stop.getStopRank());
        stopService.saveStop(stop);
        System.out.println("Working3");
        List<Stop> stopList = stopService.findAllStops();
        System.out.println("Working4");
        model.addAttribute("stopList", stopList);
        System.out.println("Working5");
        return "stopListView";
    }
}
