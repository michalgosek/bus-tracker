package com.example.bustracker.authentication;

import com.example.bustracker.dao.ApplicationUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    public ApplicationUserService(@Qualifier("MySQL") ApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    private final ApplicationUserDAO applicationUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDAO.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("% not found.%n", username)));
    }
}
