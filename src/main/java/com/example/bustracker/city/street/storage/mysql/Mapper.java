package com.example.bustracker.city.street.storage.mysql;

import com.example.bustracker.city.street.Street;
import org.springframework.jdbc.core.RowMapper;

public final class Mapper {
    static RowMapper<Street> MapStreet() {
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final String streetName = rs.getString("name");
            return new Street(id, streetName);
        };
    }
    static RowMapper<Street> MapStreets(){
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final String streetName = rs.getString("name");
            return new Street(id, streetName);
        };
    }
}
