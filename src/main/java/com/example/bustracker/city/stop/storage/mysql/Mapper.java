package com.example.bustracker.city.stop.storage.mysql;

import com.example.bustracker.city.stop.Stop;
import org.springframework.jdbc.core.RowMapper;

public final class Mapper {
    static RowMapper<Stop> MapStop() {
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final Long streetId = rs.getLong("street_id");
            final String stopName = rs.getString("name");
            return new Stop(id, stopName, streetId);
        };
    }

    static RowMapper<Stop> MapStops() {
       return MapStop();
    }
}