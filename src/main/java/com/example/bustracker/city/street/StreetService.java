package com.example.bustracker.city.street;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService {
    private final StreetDataAccessService streetDataAccessService;

    @Autowired
    public StreetService(StreetDataAccessService streetDataAccessService) {
        this.streetDataAccessService = streetDataAccessService;
    }

    List<Street> getAllStreets(){
        return streetDataAccessService.selectStreets();
    }
}
