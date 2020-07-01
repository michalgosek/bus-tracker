package com.example.bustracker.city.street;

import com.example.bustracker.city.stop.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/streets")
public class StreetManagementController {

    private final StreetService streetService;
    private final StopService stopService;

    private Street street;
    private List<Street> streetList;

    @Autowired
    public StreetManagementController(StreetService streetService, StopService stopService) {
        this.stopService = stopService;
        this.streetService = streetService;
        this.street = new Street();
    }

    @GetMapping
    public String getStreets(Model model) {
        streetList = streetService.getAllStreets();
        model.addAttribute("street", street);
        model.addAttribute("streets", streetList);
        return "account/administrator/street";
    }

    @PostMapping("search")
    public String findStreet(@RequestParam String streetName) {
        streetService.getStreetByName(streetName).ifPresent(value -> this.street = value);
        return "redirect:/api/v1/streets/";
    }

    @PostMapping("insert")
    public String insertStreet(@ModelAttribute Street street) {
        Optional<Street> target = streetService.getStreetByName(street.getName());
        final boolean insert = street.getName().length() > 5 && target.isEmpty();
        if (insert)
            streetService.insertStreet(street);

        return "redirect:/api/v1/streets/";
    }

    @PostMapping("remove")
    public String removeStreet(@ModelAttribute Street street) {
        Optional<Street> target = streetService.getStreetByName(street.getName());
        if (target.isPresent()) {
            stopService.deleteStopsWithStreetId(target.get().getId());
            streetService.deleteStreet(target.get().getId());
        }

        return "redirect:/api/v1/streets/";
    }
}
