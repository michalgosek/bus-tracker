package com.example.bustracker.city.stop;

import com.example.bustracker.city.street.Street;
import com.example.bustracker.city.street.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("stops")
public class StopController {

    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping
    public String getStops(Model model){
        List<Stop> stopList = stopService.getAllStops();
        model.addAttribute("stops", stopList);
        return "stops";
    }

    @GetMapping("/{stopId}" )
    public String getStreetView(Model model, @PathVariable("streetId") Long streetId) {
        model.addAttribute("streetId", streetId);
        return "stop";
    }


}
