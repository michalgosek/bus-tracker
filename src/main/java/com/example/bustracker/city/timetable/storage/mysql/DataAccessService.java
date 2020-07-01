package com.example.bustracker.city.timetable.storage.mysql;

import com.example.bustracker.city.timetable.Schedule;
import com.example.bustracker.dao.TimetableDAO;
import com.example.bustracker.extractor.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MySQLTimetable")
public class DataAccessService implements TimetableDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ResultSetExtractor<Optional<Schedule>> EXTRACTOR_TIME = Extractor.singletonOptionalExtractor(Mapper.MapTime());

    @Autowired
    public DataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Schedule> getTimeScheduleByLineId(Long id) {
        final String query = "SELECT * FROM time_schedule WHERE line_id = ?";
        return jdbcTemplate.query(query, Mapper.MapTimes(), id);
    }

    @Override
    public int insertTime(Schedule schedule, Long id) {
        final String query = "INSERT INTO time_schedule (id, day, time, line_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, schedule.getId(), schedule.getTime(), id);
    }

    @Override
    public int deleteTime(Long id) {
        final String query = "DELETE FROM time_schedule WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }
}
