package com.sda.nco_bus_service.controller;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.model.Company;
import com.sda.nco_bus_service.service.BusService;
import com.sda.nco_bus_service.service.CompanyService;
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

    @RequestMapping("/findAll")
    public String findAllBuses(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        return "busListView";
    }

    @RequestMapping(value="/{idBus}")
    public String findCompanyById(ModelMap model, @PathVariable("idBus") Integer idBus) {
        Bus bus = busService.findById(idBus);
        model.addAttribute("bus", bus);
        return "busView";
    }

    @RequestMapping(value = "/delete/{idBus}", method = RequestMethod.GET)
    public String deleteBus(ModelMap model, @PathVariable("idBus") Integer idBus) {
        busService.deleteBus(idBus);
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        //return redirect ???
        return "redirect:/bus/findAll";
    }

//    @RequestMapping(value = "/delete/{idCompany}", method = RequestMethod.GET)
//    public String deleteCompany(ModelMap model, @PathVariable("idCompany") Integer idCompany) {
//        companyService.deleteCompany(idCompany);
//        List<Company> companyList = companyService.findAllCompanies();
//        model.addAttribute("companyList", companyList);
//        return "companyListView";
//    }


    @RequestMapping(value = "/addBus", method = RequestMethod.GET)
    public String addBus(ModelMap model) {
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("bus", new Bus());
        model.addAttribute("companyList", companyList);
        return "addBus";
    }

    @RequestMapping(value = "/addBus", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "bus") Bus bus, ModelMap model) {
        busService.saveBus(bus);
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        return "busListView";
    }

    @RequestMapping(value = "/update/{idBus}", method = RequestMethod.GET)
    public String updateBusView(ModelMap model,  @PathVariable("idBus") Integer idBus) {
        Bus temp = busService.findById(idBus);
        model.addAttribute("bus", temp);
        return "updateBus";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBusSave(ModelMap model, @ModelAttribute(name = "bus") Bus bus) {
        busService.updateBus(bus);
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        return "redirect:/bus/findAll";
    }
}
