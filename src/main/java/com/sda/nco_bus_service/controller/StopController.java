package com.sda.nco_bus_service.controller;

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
    public String addBus(ModelMap model) {
        model.addAttribute("stop", new Stop());
        model.addAttribute("busList", busService.findAllBuses());
        model.addAttribute("companyList", companyService.findAllCompanies());

        return "addStop";
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "stop") Stop stop, ModelMap model) {
        stopService.save(stop);
        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("stopList", stopList);
        return "stopListView";
    }
}
