package com.example.bustracker.city.stop;

import com.example.bustracker.dao.StopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StopService {
    private final StopDAO stopDAO;

    @Autowired
    public StopService(@Qualifier("MySQLStop") StopDAO stopDAO) {
        this.stopDAO = stopDAO;
    }

    public List<Stop> getAllStops() {
        return stopDAO.getAllStops();
    }

    public List<Stop> getStopsWithStreetId(Long streetId) {
        return stopDAO.getStopsWithStreetId(streetId);
    }

    public Optional<Stop> getStopByName(String stopName) {
        return stopDAO.getStopByName(stopName);
    }

    public void insertStop(Stop stop, Long id) {
        stopDAO.insertStop(stop, id);
    }

    public void deleteStop(Long id) {
        stopDAO.deleteStop(id);
    }

    public void deleteStopsWithStreetId(Long id) { stopDAO.deleteStopsWithStreetId(id); }
}

