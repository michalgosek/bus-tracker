package com.example.bustracker.dao;

import com.example.bustracker.city.stop.Stop;

import java.util.List;
import java.util.Optional;

public interface StopDAO {
    List<Stop> getAllStops();

    List<Stop> getStopsWithStreetId(Long id);

    Optional<Stop> getStopById(Long id);

    Optional<Stop> getStopByName(String stopName);

    int insertStop(Stop stop, Long id);

    int deleteStop(Long id);

    int deleteStopsWithStreetId(Long id);
}