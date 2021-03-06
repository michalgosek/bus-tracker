package com.example.bustracker.dao;


import com.example.bustracker.city.street.Street;

import java.util.List;
import java.util.Optional;

public interface StreetDAO {
    List<Street> getAllStreets();
    Optional<Street> getStreetByName(String streetName);
    int insertStreet(Street street);
    int deleteStreet(Long streetId);
}
