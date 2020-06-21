package com.example.bustracker.city.street.dataaccess;

import com.example.bustracker.city.street.Street;
import com.example.bustracker.dao.StreetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MySQLStreet")
public class MySQLDataAccessService implements StreetDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySQLDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Street> selectStreets(){
        final String query = "SELECT * FROM streets";
        return jdbcTemplate.query(query, mapStreetFromDatabse());
    }

    private RowMapper<Street> mapStreetFromDatabse(){
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final String streetName = rs.getString("name");
            return new Street(id, streetName);
        };
    }
}
