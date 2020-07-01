package com.example.bustracker.city.line.storage.mysql;

import com.example.bustracker.city.line.Line;
import com.example.bustracker.dao.LineDAO;
import com.example.bustracker.extractor.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MySQLBusLine")
public class DataAccessService implements LineDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ResultSetExtractor<Optional<Line>> EXTRACTOR_LINE = Extractor.singletonOptionalExtractor(Mapper.MapLine());

    @Autowired
    public DataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Line> getAllLines() {
        final String query = "SELECT * FROM bus_lines";
        return jdbcTemplate.query(query, Mapper.MapLines());
    }

    @Override
    public List<Line> getAllLinesByStopId(Long id) {
        final String query = "SELECT * FROM bus_lines WHERE stop_id = ?";
        return jdbcTemplate.query(query, Mapper.MapLines(), id);
    }

    @Override
    public Optional<Line> getLineByDirection(String direction) {
        final String query = "SELECT * FROM bus_lines WHERE direction = ?";
        return jdbcTemplate.query(query, EXTRACTOR_LINE, direction);
    }

    @Override
    public Optional<Line> getLineById(Long id) {
        final String query = "SELECT * FROM bus_lines WHERE id = ?";
        return jdbcTemplate.query(query, EXTRACTOR_LINE, id);
    }

    @Override
    public Optional<Line> getLineByStopId(Long id) {
        final String query = "SELECT * FROM bus_lines WHERE stop_id = ?";
        return jdbcTemplate.query(query, EXTRACTOR_LINE, id);
    }

    @Override
    public int insertLine(Line line,  Long id) {
        final String query = "INSERT INTO bus_lines (id, direction, line_number, stop_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, line.getId(), line.getDirection(), line.getLineNumber(), id);
    }

    @Override
    public int deleteLine(Long id) {
        final String query = "DELETE FROM bus_lines WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }
}
