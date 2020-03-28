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

    @RequestMapping("/findAll")
    public String findAllBuses(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
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
        return "redirect:http://localhost:8080/buses/findAll";
    }

//    @RequestMapping(value = "/delete/{idCompany}", method = RequestMethod.GET)
//    public String deleteCompany(ModelMap model, @PathVariable("idCompany") Integer idCompany) {
//        companyService.deleteCompany(idCompany);
//        List<Company> companyList = companyService.findAllCompanies();
//        model.addAttribute("companyList", companyList);
//        return "companyListView";
//    }


    // new add bus! only name and ID

    @RequestMapping(value = "/addBus", method = RequestMethod.GET)
    public String addBus(ModelMap model) {
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("bus", new Bus());
        model.addAttribute("companyList", companyList);

        // newly added - something fishy here
        model.addAttribute("stop", new Stop());
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        return "addBus";
    }

    @RequestMapping(value = "/addBus", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "bus") Bus bus, ModelMap model) {
        busService.saveBus(bus);
        idOfBus = bus.getIdBus();
        System.out.println("id bus:" + idOfBus);

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        return "redirect:http://localhost:8080/buses/addBus/";
    }

    @ModelAttribute
    public void addAttributes(ModelMap model) {
        model.addAttribute("idOfBus", idOfBus);
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.POST)
    public String addStop(@ModelAttribute(name = "stop") Stop stop, ModelMap model) {
        stopService.saveStop(stop);
        System.out.println("id bus:" + idOfBus);

        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("stopList", stopList);
        List<Company> companyList = companyService.findAllCompanies();
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("idOfBus", idOfBus);
        return "redirect:/buses/addBus";
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
        return "redirect:/buses/findAll";
    }
}
