package com.example.bustracker.city.timetable;

import com.example.bustracker.dao.TimetableDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    private final TimetableDAO timetableDAO;

    public ScheduleService(@Qualifier("MySQLTimetable") TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }

    public List<Schedule> getTimeScheduleByStopId(Long id) {
        return timetableDAO.getTimeScheduleByLineId(id);
    }

    public int insertTime(Schedule schedule, Long id) {
        return timetableDAO.insertTime(schedule, id);
    }

    public int deleteTime(Long id) {
        return timetableDAO.deleteTime(id);
    }
}
