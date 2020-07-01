package com.example.bustracker.city.line;

import com.example.bustracker.city.stop.Stop;
import com.example.bustracker.city.street.Street;
import com.example.bustracker.dao.LineDAO;
import com.example.bustracker.dao.StopDAO;
import com.example.bustracker.dao.StreetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("line")
public class LineController {

    private final LineDAO lineDAO;
    private final StopDAO stopDAO;
    private final StreetDAO streetDAO;

    @Autowired
    public LineController(@Qualifier("MySQLBusLine") LineDAO lineDAO, StopDAO stopDAO, StreetDAO streetDAO) {
        this.lineDAO = lineDAO;
        this.stopDAO = stopDAO;
        this.streetDAO = streetDAO;
    }

    @GetMapping(path = "{stopId}")
    public String getLine(Model model, @PathVariable Long stopId){
        List<Line> lines = lineDAO.getAllLinesByStopId(stopId);
        model.addAttribute("lines", lines);

        Optional<Stop> stop = stopDAO.getStopById(stopId);
        stop.ifPresent(value -> model.addAttribute("stopName", value.getName()));
        return "line";
    }
}
