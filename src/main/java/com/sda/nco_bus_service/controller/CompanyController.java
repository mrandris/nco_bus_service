package com.sda.nco_bus_service.controller;

import com.sda.nco_bus_service.model.Bus;
import com.sda.nco_bus_service.model.Company;
import com.sda.nco_bus_service.service.BusService;
import com.sda.nco_bus_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private BusService busService;

    @RequestMapping("/findAll")
    public String findAllCompanies(ModelMap model) {
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);
        return "companyListView";
    }

    @RequestMapping(value="/{idCompany}")
    public String findCompanyById(ModelMap model, @PathVariable("idCompany") Integer idCompany) {
        Company company = companyService.findById(idCompany);
        model.addAttribute("company", company);
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);
        return "companyView";
    }

    @RequestMapping(value = "/delete/{idCompany}", method = RequestMethod.GET)
    public String deleteCompany(ModelMap model, @PathVariable("idCompany") Integer idCompany) {
        companyService.deleteCompany(idCompany);
        return "redirect:http://localhost:8080/companies/findAll";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.GET)
    public String addCompany(ModelMap model) {
        model.addAttribute("company", new Company());
        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);
        return "addCompany";
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public String create(@ModelAttribute(name = "company") Company company, ModelMap model) {
        companyService.saveCompany(company);
        return "redirect:http://localhost:8080/companies/findAll";
    }

    @RequestMapping(value = "/update/{idCompany}", method = RequestMethod.GET)
    public String updateCompanyView(ModelMap model,  @PathVariable("idCompany") Integer idCompany) {
        Company temp = companyService.findById(idCompany);
        model.addAttribute("company", temp);

        List<Bus> busList = busService.findAllBuses();
        model.addAttribute("busList", busList);
        List<Company> companyList = companyService.findAllCompanies();
        model.addAttribute("companyList", companyList);
        return "updateCompany";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCompanySave(ModelMap model, @ModelAttribute(name = "company") Company company) {
        companyService.updateCompany(company);
        return "redirect:http://localhost:8080/companies/findAll";
    }
}
