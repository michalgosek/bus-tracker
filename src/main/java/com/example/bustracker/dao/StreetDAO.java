package com.example.bustracker.dao;


import com.example.bustracker.city.street.Street;

import java.util.List;

public interface StreetDAO {
    List<Street> selectStreets();
}
