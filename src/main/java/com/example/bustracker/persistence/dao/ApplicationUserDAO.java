package com.example.bustracker.persistence.dao;

import com.example.bustracker.authentication.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<ApplicationUser> loadUserByUsername(String username);
}
