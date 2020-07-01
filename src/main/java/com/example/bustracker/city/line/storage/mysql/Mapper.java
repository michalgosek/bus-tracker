package com.example.bustracker.city.line.storage.mysql;

import com.example.bustracker.city.line.Line;
import org.springframework.jdbc.core.RowMapper;

public final class Mapper {
    static RowMapper<Line> MapLine() {
        return (rs, rowNum) -> {
            final Long id = rs.getLong("id");
            final Long stopId = rs.getLong("stop_id");
            final String direction = rs.getString("direction");
            final Integer lineNumber = rs.getInt("line_number");

            return new Line(id, direction, lineNumber, stopId);
        };
    }

    static RowMapper<Line> MapLines() {
        return MapLine();
    }
}