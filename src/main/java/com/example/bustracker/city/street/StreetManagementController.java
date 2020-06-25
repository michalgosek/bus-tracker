package com.example.bustracker.city.street;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/streets")
public class StreetManagementController {

    private final StreetService streetService;
    private Street street;
    private List<Street> streetList;

    @Autowired
    public StreetManagementController(StreetService streetService) {
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
    public String findStreet(@ModelAttribute Street street) {
        streetService.getStreetByName(street.getName()).ifPresent(value -> this.street = value);
        return "redirect:/api/v1/streets/";
    }

    @PostMapping("insert")
    public String addStreet(@ModelAttribute Street street, Model model) {
        Optional<Street> target = streetService.getStreetByName(street.getName());
        final boolean insert = street.getName().length() > 5 && target.isEmpty();
        if (insert)
            streetService.insertStreet(street);

        return "redirect:/api/v1/streets/";
    }

    @PostMapping("remove")
    public String removeStreet(@ModelAttribute Street street) {
        Optional<Street> target = streetService.getStreetByName(street.getName());
        target.ifPresent(value -> streetService.deleteStreet(value.getId()));
        return "redirect:/api/v1/streets/";
    }
}
