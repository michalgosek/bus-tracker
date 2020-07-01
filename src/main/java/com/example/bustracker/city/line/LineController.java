package com.example.bustracker.city.line;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lines")
public class LineController {

    @GetMapping
    public String getLines(Model model){
        return "lines";
    }
}
