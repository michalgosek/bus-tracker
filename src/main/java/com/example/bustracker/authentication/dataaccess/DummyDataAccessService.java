package com.example.bustracker.authentication.dataaccess;

import com.example.bustracker.authentication.ApplicationUser;
import com.example.bustracker.authentication.User;
import com.example.bustracker.dao.ApplicationUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("dummy")
public class DummyDataAccessService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DummyDataAccessService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private List<ApplicationUser> getApplicationUsers() {
        User user = new User(
                Long.valueOf(1),
                "admin",
                passwordEncoder.encode("pass"),
                true,
                true,
                true, true);

        List<ApplicationUser> applicationUsers = List.of(
                new ApplicationUser(user, Collections.singleton(new SimpleGrantedAuthority("ADMIN"))));
        return applicationUsers;
    }

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
}

