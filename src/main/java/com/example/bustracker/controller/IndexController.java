package com.example.bustracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("index")
    public String getIndexView(){
        return "index";
    }

    @GetMapping("admin")
    public String getAdminView(){
        return "dashboard";
    }
}
