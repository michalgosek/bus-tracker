package com.example.bustracker.city.stop.storage.mysql;

import com.example.bustracker.city.stop.Stop;
import com.example.bustracker.city.street.Street;
import com.example.bustracker.dao.StopDAO;
import com.example.bustracker.extractor.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MySQLStop")
public class DataAccessService implements StopDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ResultSetExtractor<Optional<Stop>> EXTRACTOR_STOP = Extractor.singletonOptionalExtractor(Mapper.MapStop());

    @Autowired
    public DataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stop> getAllStops() {
        final String query = "SELECT * FROM stops";
        return jdbcTemplate.query(query, Mapper.MapStops());
    }

    @Override
    public Optional<Stop> getStopByName(String stopName) {
        final String query = "SELECT * FROM stops WHERE name = ?";
        return jdbcTemplate.query(query, EXTRACTOR_STOP, stopName);
    }

    @Override
    public Optional<Stop> getStopById(Long id) {
        final String query = "SELECT * FROM stops WHERE id = ?";
        return jdbcTemplate.query(query, EXTRACTOR_STOP, id);
    }

    @Override
    public List<Stop> getStopsWithStreetId(Long id) {
        final String query = "SELECT * FROM stops WHERE street_id = ?";
        return jdbcTemplate.query(query, Mapper.MapStops(), id);
    }

    @Override
    public int insertStop(Stop stop, Long id) {
        final String query = "INSERT INTO stops (id, name, street_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, stop.getId(), stop.getName(), id);
    }

    @Override
    public int deleteStop(Long id) {
        final String query = "DELETE FROM stops WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public int deleteStopsWithStreetId(Long id) {
        final String query = "DELETE FROM stops WHERE street_id = ?";
        return jdbcTemplate.update(query, id);
    }
}
