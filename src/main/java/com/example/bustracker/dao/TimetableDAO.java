package com.example.bustracker.dao;

import com.example.bustracker.city.timetable.Schedule;

import java.util.List;

public interface TimetableDAO {
    List<Schedule> getTimeScheduleByLineId(Long id);
    int insertTime(Schedule schedule, Long id);
    int deleteTime(Long id);
}
