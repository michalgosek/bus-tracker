package com.example.bustracker.city.street;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/streets/")
public class StreetManagementController {

    private final StreetService streetService;
    private Street street;

    @Autowired
    public StreetManagementController(StreetService streetService) {
        this.streetService = streetService;
        this.street = new Street((long) -1, "");
    }

    @GetMapping("get")
    public String getStreets(Model model) {
//        List<Street> streetList = streetService.getAllStreets();
//        model.addAttribute("streets", streetList);
//        model.addAttribute("street", new Street());
        model.addAttribute("street", street);
        return "account/administrator/add_street";
    }

    @PostMapping("add")
    public String addStreet(@ModelAttribute Street street) {
        Optional<Street> res = streetService.getStreetByName(street.getName());
        res.ifPresentOrElse(value -> {
            street.setId(value.getId());
            street.setName(value.getName());
        }, () -> {
            street.setId((long) -1);
            street.setName("");
        });
        return "account/administrator/add_street";
    }
}
