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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController {
    @Autowired
    private BusService busService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StopService stopService;

    private Integer idOfBus;
    private String nameOfBus;

    @RequestMapping("/findAll")
    public String findAllBuses(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        return "busListView";
    }

    @RequestMapping(value="/{idBus}")
    public String findBusById(ModelMap model, @PathVariable("idBus") Integer idBus) {
        Bus bus = busService.findById(idBus);
        model.addAttribute("bus", bus);

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        return "busView";
    }

    @RequestMapping(value = "/delete/{idBus}", method = RequestMethod.GET)
    public String deleteBus(ModelMap model, @PathVariable("idBus") Integer idBus) {
        busService.deleteBus(idBus);
        return "redirect:http://localhost:8080/buses/findAll";
    }

    // pass only name and ID of bus
    @RequestMapping(value = "/addBus", method = RequestMethod.GET)
    public String addBus(ModelMap model) {
        model.addAttribute("bus", new Bus());

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        return "addBus";
    }

    // save bus object with only name and ID
    @RequestMapping(value = "/addBus", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "bus") Bus bus, ModelMap model) {
        busService.saveBus(bus);

        idOfBus = bus.getIdBus();
        model.addAttribute("idOfBus", idOfBus);
        nameOfBus = bus.getBusName();
        model.addAttribute("nameOfBus", nameOfBus);

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        return "redirect:http://localhost:8080/buses/addStop/";
    }

    @RequestMapping(value = "/update/{idBus}", method = RequestMethod.GET)
    public String updateBusView(ModelMap model,  @PathVariable("idBus") Integer idBus) {
        Bus temp = busService.findById(idBus);
        model.addAttribute("bus", temp);
        model.addAttribute("stop", new Stop());

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        return "updateBus";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBusSave(@ModelAttribute(name = "bus") Bus bus, ModelMap model) {
        idOfBus = bus.getIdBus();
        busService.updateBus(bus);
//        busService.updateBus(busService.findById(idOfBus));

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

        model.addAttribute("idOfBus", idOfBus);
        nameOfBus = bus.getBusName();
        model.addAttribute("nameOfBus", nameOfBus);

        return "redirect:http://localhost:8080/buses/addStop/";
    }

    @ModelAttribute
    public void addAttributes(ModelMap model) {
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.GET)
    public String addStop(ModelMap model) {
        model.addAttribute("stop", new Stop());

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

//        return "redirect:http://localhost:8080/buses/addBus/";
        return "addStop";
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.POST)
    public String createStop(@ModelAttribute(name = "stop") Stop stop, ModelMap model) {
        stopService.saveStop(stop);
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);

//        return "redirect:http://localhost:8080/buses/addBus/";
        return "redirect:http://localhost:8080/buses/addStop/";
    }


    @RequestMapping(value = "/deleteStop/{idStop}", method = RequestMethod.GET)
    public String deleteStop(ModelMap model, @PathVariable("idStop") Integer idStop) {
        stopService.deleteStop(idStop);
        return "redirect:http://localhost:8080/buses/addStop/";
    }

    int i = 0;
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runApp(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();

        while(true) {
            for(Bus bus : busList) {
                if(bus.getStopList().size() != 0) {
                    walkBus(i, bus);
                }
            }
            i++;

        return "redirect:http://localhost:8080/index";
        }
    }

    public void walkBus(int i, Bus bus) {
        List<Stop> stopList = bus.getStopList();
        int j = i%stopList.size();

        for(Stop stop : bus.getStopList()) {
            stop.setIsPresent(0);
            if(stopList.indexOf(stop) == j) {
                stop.setIsPresent(1);
            }
            stopService.saveStop(stop);
        }
    }
}
