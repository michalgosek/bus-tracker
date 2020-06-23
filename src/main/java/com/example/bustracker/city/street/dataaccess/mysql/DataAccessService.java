package com.example.bustracker.city.street.dataaccess.mysql;

import com.example.bustracker.city.street.Street;
import com.example.bustracker.dao.StreetDAO;
import com.example.bustracker.extractor.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MySQLStreet")
public class DataAccessService implements StreetDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ResultSetExtractor<Optional<Street>> EXTRACTOR_STREET = Extractor.singletonOptionalExtractor(Mapper.MapStreet());

    @Autowired
    public DataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Street> getAllStreets() {
        final String query = "SELECT * FROM streets";
        return jdbcTemplate.query(query, Mapper.MapStreets());
    }

    @Override
    public Optional<Street> getStreetByName(String streetName) {
        final String query = "SELECT * FROM streets WHERE name = ?";
        return jdbcTemplate.query(query, EXTRACTOR_STREET, streetName);
    }

    @Override
    public int insertStreet(Street street) {
        final String query = "INSERT INTO streets (id, name) VALUES (?, ?)";
        return jdbcTemplate.update(query, street.getId(), street.getName());
    }
}
