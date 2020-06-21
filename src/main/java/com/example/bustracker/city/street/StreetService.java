package com.example.bustracker.city.street;

import com.example.bustracker.city.street.dataaccess.MySQLDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StreetService implements StreetDetailsService {

    private final MySQLDataAccessService mySQLDataAccessService;

    @Autowired
    public StreetService(@Qualifier("MySQLStreet") MySQLDataAccessService mySQLDataAccessService) {
        this.mySQLDataAccessService = mySQLDataAccessService;
    }

    @Override
    public List<Street> getAllStreets() {
        return mySQLDataAccessService.selectStreets();
    }
}
