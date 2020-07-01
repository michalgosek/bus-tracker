package com.example.bustracker.dao;

import com.example.bustracker.city.line.Line;

import java.util.List;
import java.util.Optional;

public interface LineDAO {
    List<Line> getAllLines();
    Optional<Line> getLineByDirection(String direction);
    Optional<Line> getLineById(Long id);
    int insertLine(Line line, Long id);
    int deleteLine(Long id);
}


