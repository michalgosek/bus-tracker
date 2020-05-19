package com.example.bustracker.authentication;

import com.example.bustracker.persistence.dao.ApplicationUserDAO;
import com.example.bustracker.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("MySQL")
public class MySQLDataAccessService implements ApplicationUserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySQLDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<User> getApplicationUsers() {
        final String query = "SELECT id, " +
                "username, " +
                "password, " +
                "is_account_non_expired, " +
                "is_account_non_locked, " +
                "is_credentials_non_expired, " +
                "is_enabled\n" +
                "FROM users;";

        return jdbcTemplate.query(query, mapUserFromDatabase());
    }

    private RowMapper<User> mapUserFromDatabase() {
        return (resultSet, i) -> {
            final Long id = resultSet.getLong("id");
            final String userName = resultSet.getString("username");
            final String password = resultSet.getString("password");
            final boolean isAccountNotExpired = resultSet.getBoolean("is_account_non_expired");
            final boolean isAccountNotLocked = resultSet.getBoolean("is_account_non_locked");
            final boolean isCredentialsNonExpired = resultSet.getBoolean("is_credentials_non_expired");
            final boolean isEnabled = resultSet.getBoolean("is_enabled");

            return new User(
                    id,
                    userName,
                    password,
                    isAccountNotExpired,
                    isAccountNotLocked,
                    isCredentialsNonExpired,
                    isEnabled
            );
        };
    }

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String username) {
        final Optional<User> appUser = getApplicationUsers()
                .stream().filter(user -> username.equals(user.getUsername())).findFirst();

        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        Optional<ApplicationUser> applicationUser = Optional.of(new ApplicationUser(appUser.get(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        return applicationUser;
    }
}
