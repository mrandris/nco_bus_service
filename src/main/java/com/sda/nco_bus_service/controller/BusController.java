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
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
        return "busListView";
    }

    @RequestMapping(value="/{idBus}")
    public String findCompanyById(ModelMap model, @PathVariable("idBus") Integer idBus) {
        List<Company> companyList = companyService.findAllCompanies();
        Bus bus = busService.findById(idBus);
        model.addAttribute("bus", bus);
        model.addAttribute("companyList", companyList);
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
        nameOfBus = bus.getBusName();
//        System.out.println("id bus:" + idOfBus);
//        System.out.println(bus.getStopList());
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
        return "redirect:http://localhost:8080/buses/addBus/";
    }

    @RequestMapping(value = "/update/{idBus}", method = RequestMethod.GET)
    public String updateBusView(ModelMap model,  @PathVariable("idBus") Integer idBus) {
        List<Company> companyList = companyService.findAllCompanies();
        List<Stop> stopList = stopService.findAllStops();
        List<Bus> busList = busService.findAllBuses();
        Bus temp = busService.findById(idBus);
        model.addAttribute("bus", temp);
        model.addAttribute("companyList", companyList);
        model.addAttribute("stopList", stopList);
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idBus);
        model.addAttribute("nameOfBus", nameOfBus);
        return "updateBus";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBusSave(@ModelAttribute(name = "bus") Bus bus, ModelMap model) {
        busService.updateBus(bus);
        idOfBus = bus.getIdBus();
        nameOfBus = bus.getBusName();
        System.out.println("id bus:" + idOfBus);
        System.out.println(bus.getStopList());
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
        return "redirect:http://localhost:8080/buses/addBus/";
    }

    @ModelAttribute
    public void addAttributes(ModelMap model) {
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
    }

    @RequestMapping(value = "/addStop", method = RequestMethod.POST)
    public String addStop(@ModelAttribute(name = "stop") Stop stop, ModelMap model) {
        List<Company> companyList = companyService.findAllCompanies();
        List<Bus> busList = busService.findAllBuses();
        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("companyList", companyList);

        stopService.saveStop(stop);
        Bus bus = busService.findById(idOfBus);

        model.addAttribute("stopList", stopList);
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
        model.addAttribute("bus", bus);
        return "redirect:/buses/addBus";
    }

    @RequestMapping(value = "update/addStop", method = RequestMethod.POST)
    public String updateAddStop(@ModelAttribute(name = "stop") Stop stop, @PathVariable("idBus") Integer idBus, ModelMap model) {
        List<Company> companyList = companyService.findAllCompanies();
        List<Bus> busList = busService.findAllBuses();
        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("companyList", companyList);

        stopService.saveStop(stop);
        Bus bus = busService.findById(idBus);

        model.addAttribute("stopList", stopList);
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idBus);
        model.addAttribute("nameOfBus", bus.getBusName());
        model.addAttribute("bus", bus);
        return "redirect:/buses/updateBus";
    }

//    do we need all attributes and lists ???

    @RequestMapping(value = "/deleteStop/{idStop}", method = RequestMethod.GET)
    public String deleteStop(ModelMap model, @PathVariable("idStop") Integer idStop) {
        System.out.println("Id stop: " + idStop);
        List<Stop> stopList = stopService.findAllStops();
        List<Bus> busList = busService.findAllBuses();
        List<Company> companyList = companyService.findAllCompanies();
        stopService.deleteStop(idStop);
        Bus bus = busService.findById(idOfBus);
        List<Stop> busStopList = bus.getStopList();
        model.addAttribute("companyList", companyList);
        model.addAttribute("stopList", busStopList);
        model.addAttribute("stopList", stopList);
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
        model.addAttribute("bus", bus);
        //return redirect ???
        return "redirect:http://localhost:8080/buses/addBus/";
    }

    @RequestMapping(value = "/update/deleteStop/{idStop}", method = RequestMethod.GET)
    public String updateDeleteStop(ModelMap model, @PathVariable("idStop") Integer idStop) {
        System.out.println("Id stop: " + idStop);
        List<Stop> stopList = stopService.findAllStops();
        List<Bus> busList = busService.findAllBuses();
        List<Company> companyList = companyService.findAllCompanies();
        stopService.deleteStop(idStop);
        Bus bus = busService.findById(idOfBus);
        List<Stop> busStopList = bus.getStopList();
        model.addAttribute("companyList", companyList);
        model.addAttribute("stopList", busStopList);
        model.addAttribute("stopList", stopList);
        model.addAttribute("busList", busList);
        model.addAttribute("idOfBus", idOfBus);
        model.addAttribute("nameOfBus", nameOfBus);
        model.addAttribute("bus", bus);
        //return redirect ???
        return "redirect:http://localhost:8080/buses/updateBus/";
    }

    int i = 0;

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runApp(ModelMap model) throws InterruptedException {
        List<Bus> busList = busService.findAllBuses();
        List<Company> companyList = companyService.findAllCompanies();
//        busList.get(1).getStopList().get(1).setPresent(true);
        while(true) {
//            Thread.sleep(3000);
            for(Bus bus : busList) {
                if(bus.getStopList().size() != 0) {
                    walkBus(i, bus);
                }
            }
            i++;
//        for(Bus bus: busList) {
//            for(Stop stop : bus.getStopList()) {
//                stop.setIsPresent(1);
//                stopService.saveStop(stop);
//            }
//        }

        List<Stop> stopList = stopService.findAllStops();
        model.addAttribute("busList", busList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("stopList", stopList);
        for(Stop stop : stopList) {
            System.out.println("Stop: " + stop.getCompany().getCompanyName() + " Is bus present: " + stop.getIsPresent());
        }
            return "redirect:http://localhost:8080/index";
        }
    }

    public void walkBus(int i, Bus bus) {
        List<Stop> stopList = bus.getStopList();
        int j = i%stopList.size();
        System.out.println("Busline: " + bus.getBusName());
        System.out.println("Stoplist size=" + stopList.size() + "\tJ=" + j);
        for(Stop stop : bus.getStopList()) {
            stop.setIsPresent(0);
            if(stopList.indexOf(stop) == j) {
                stop.setIsPresent(1);
            }
            stopService.saveStop(stop);
        }
        printStopList(bus);
        System.out.println();
    }

    public void printStopList(Bus bus) {
        for(Stop stop : bus.getStopList()) {
            System.out.println("StopName: " + stop.getCompany().getCompanyName() + "\tRank: " + bus.getStopList().indexOf(stop) + "\tIsPresent: " + stop.getIsPresent());
        }
    }
}
