package com.example.bustracker.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ApplicationMainController {

    @GetMapping("index")
    public String getIndexView(){
        return "index";
    }

    @GetMapping("dashboard")
    public String getAdminView(){
        return "account/dashboard";
    }
}
