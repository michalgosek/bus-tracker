package com.example.bustracker.city.street;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/streets")
public class StreetController {

    private final StreetService streetService;

    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public String getStreets(Model model){
            List<Street> streetList = streetService.getAllStreets();
            model.addAttribute("streets", streetList);
            return "street";
    }
}
