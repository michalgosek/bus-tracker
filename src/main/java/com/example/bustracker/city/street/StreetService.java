package com.example.bustracker.city.street;

import com.example.bustracker.dao.StreetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StreetService  {
    private final StreetDAO streetDAO;

    @Autowired
    public StreetService(@Qualifier("MySQLStreet") StreetDAO streetDAO) {
        this.streetDAO = streetDAO;
    }

    public List<Street> getAllStreets() {
        return streetDAO.getAllStreets();
    }

    public Optional<Street> getStreetByName(String streetName) {
        return streetDAO.getStreetByName(streetName);
    }

    public void insertStudent(Street street) {
        streetDAO.insertStreet(street);
    }
}
