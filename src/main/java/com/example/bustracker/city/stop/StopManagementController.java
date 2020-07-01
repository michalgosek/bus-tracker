package com.example.bustracker.city.stop;

import com.example.bustracker.city.street.Street;
import com.example.bustracker.city.street.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/stops")
public class StopManagementController {

    private final StopService stopService;
    private final StreetService streetService;
    private Stop stop;
    private Long streetId;

    @Autowired
    public StopManagementController(StopService stopService, StreetService streetService) {
        this.streetService = streetService;
        this.stopService = stopService;
        this.stop = new Stop();
    }

    @GetMapping
    public String getStops(Model model) {
        List<Stop> stopList = stopService.getAllStops();
        model.addAttribute("stop", stop);
        model.addAttribute("stops", stopList);
        model.addAttribute("streetId", streetId);
        return "account/administrator/stop";
    }

    @PostMapping("search")
    public String findStop(@ModelAttribute Stop stop) {
        Optional<Stop> target = stopService.getStopByName(stop.getName());
        if (target.isPresent()) {
            this.stop = target.get();
            this.streetId = target.get().getStreet().getId();
        }

        return "redirect:/api/v1/stops/";
    }

    @PostMapping("insert")
    public String insertStop(@ModelAttribute Stop stop, @RequestParam Long streetId) {
        Optional<Stop> target = stopService.getStopByName(stop.getName());
        final boolean insert = stop.getName().length() > 5 && target.isPresent();
        if (insert) {
            stopService.insertStop(stop, streetId);
        }

        return "redirect:/api/v1/stops/";
    }

    @PostMapping("remove")
    public String removeStop(@ModelAttribute Stop stop) {
        Optional<Stop> target = stopService.getStopByName(stop.getName());
        target.ifPresent(value -> stopService.deleteStop(target.get().getId()));

        return "redirect:/api/v1/stops/";
    }
}
