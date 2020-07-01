package com.example.bustracker.city.line;

import com.example.bustracker.dao.LineDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LineService {
    private final LineDAO lineDAO;

    @Autowired
    public LineService(@Qualifier("MySQLBusLine") LineDAO lineDAO) {
        this.lineDAO = lineDAO;
    }

    public List<Line> getAllLines() {
        return lineDAO.getAllLines();
    }

    public List<Line> getAllLinesByStopId(Long id) { return  lineDAO.getAllLinesByStopId(id); }

    public Optional<Line> getLineByDirection(String direction) {
        return lineDAO.getLineByDirection(direction);
    }

    public Optional<Line> getLineByStopId(Long id) { return lineDAO.getLineByStopId(id); }

    public Optional<Line> getLineById(Long id) {
        return lineDAO.getLineById(id);
    }

    public int insertLine(Line line, Long id) {
        return lineDAO.insertLine(line, id);
    }

    public int deleteLine(Long id) {
        return lineDAO.deleteLine(id);
    }
}
