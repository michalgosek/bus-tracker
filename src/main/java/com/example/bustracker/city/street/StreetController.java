package com.example.bustracker.city.street;

import com.example.bustracker.city.stop.Stop;
import com.example.bustracker.city.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("streets")
public class StreetController {

    private final StreetService streetService;
    private final StopService stopService;

    @Autowired
    public StreetController(StreetService streetService, StopService stopService) {
        this.streetService = streetService;
        this.stopService = stopService;
    }

    @GetMapping
    public String getStreets(Model model) {
        List<Street> streetList = streetService.getAllStreets();
        model.addAttribute("streets", streetList);
        return "streets";
    }

    @GetMapping("/{streetId}")
    public String getStreetView(Model model, @PathVariable("streetId") Long streetId) {
        Optional<Street> street = streetService.getStreetById(streetId);
        street.ifPresent(value -> model.addAttribute("streetName", value.getName()));

        List<Stop> stopList = stopService.getStopsWithStreetId(streetId);
        if (!stopList.isEmpty()) {
            model.addAttribute("stops", stopList);
        }

        return "stop";
    }
}
