package com.example.bustracker.city.schedule;

import com.example.bustracker.city.line.Line;
import com.example.bustracker.dao.LineDAO;
import com.example.bustracker.dao.TimetableDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    private final TimetableDAO timetableDAO;
    private final LineDAO lineDAO;

    @Autowired
    public ScheduleController(TimetableDAO timetableDAO, LineDAO lineDAO) {
        this.timetableDAO = timetableDAO;
        this.lineDAO = lineDAO;
    }

    @GetMapping(path = "{lineId}")
    public String getTimeSchedule(Model model, @PathVariable Long lineId) {
        List<Schedule> schedules = timetableDAO.getTimeScheduleByLineId(lineId);
        Optional<Line> line = lineDAO.getLineById(lineId);
        line.ifPresent(value -> model.addAttribute("direction", value.getDirection()));

        model.addAttribute("schedules", schedules);
        return "time_schedule";
    }
}
