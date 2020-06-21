package com.example.bustracker.dao;

import com.example.bustracker.authentication.ApplicationUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface ApplicationUserDAO{
    Optional<ApplicationUser> loadUserByUsername(String username) throws UsernameNotFoundException;
}
