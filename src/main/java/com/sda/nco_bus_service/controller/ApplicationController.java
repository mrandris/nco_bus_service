package com.sda.nco_bus_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {
    @RequestMapping("/index")
    public String findAllRoutes() {
        return "index";
    }
}
