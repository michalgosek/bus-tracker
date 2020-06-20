package com.example.bustracker.city.street;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/streets/")
public class TestController {

    @GetMapping("get")
    public String getStreets2(){
        return "account/administrator/add_street";
    }
}
