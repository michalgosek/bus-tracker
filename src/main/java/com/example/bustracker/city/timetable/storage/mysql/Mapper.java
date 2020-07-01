package com.example.bustracker.city.timetable.storage.mysql;

import com.example.bustracker.city.timetable.Schedule;
import org.springframework.jdbc.core.RowMapper;

public final class Mapper {
    static RowMapper<Schedule> MapTime() {
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final Integer day = rs.getInt("day");
            final String time = rs.getString("time");
            final Long lineId = rs.getLong("line_id");

            return new Schedule(id, day, time, lineId);
        };
    }

    static RowMapper<Schedule> MapTimes() {
        return MapTime();
    }
}