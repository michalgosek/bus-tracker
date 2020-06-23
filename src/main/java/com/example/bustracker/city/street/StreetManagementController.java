package com.example.bustracker.city.street;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/streets/")
public class StreetManagementController {

    private final StreetService streetService;
    private Street street;
    private List<Street> streetList;

    @Autowired
    public StreetManagementController(StreetService streetService) {
        this.streetService = streetService;
        this.street = new Street((long) -1, "");

    }

    @GetMapping("get")
    public String getStreets(Model model) {
        streetList = streetService.getAllStreets();
        model.addAttribute("streets", streetList);
        model.addAttribute("street", street);
        return "account/administrator/add_street";
    }

    @PostMapping("insert")
    public String addStreet(@ModelAttribute Street street, Model model) {
        streetList = streetService.getAllStreets();
        model.addAttribute("streets", streetList);
        Optional<Street> res = streetService.getStreetByName(street.getName());

        res.ifPresentOrElse(value -> {
            street.setId(value.getId());
            street.setName(value.getName());
        }, () -> {

            // todo find better approach!
            if (!street.getName().isEmpty() && street.getName().length() > 5) {
                streetService.insertStudent(street);
            }

            street.setId((long) -1);
            street.setName("");
        });

        return "account/administrator/add_street";
    }


    @PostMapping("remove")
    public String removeStreet(@ModelAttribute Street street) {
        System.out.println(street);
        return "account/administrator/add_street";
    }
}
