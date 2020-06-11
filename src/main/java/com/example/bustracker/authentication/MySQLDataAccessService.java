package com.example.bustracker.authentication;

import com.example.bustracker.persistence.dao.ApplicationUserDAO;
import com.example.bustracker.persistence.model.Role;
import com.example.bustracker.persistence.model.User;
import com.example.bustracker.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

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

    private List<Role> getApplicationRoles() {
        final String query = "SELECT id, name FROM roles";
        return jdbcTemplate.query(query, mapRolesFromDatabase());
    }

    private List<ApplicationUserRole> getApplicationUserRole() {
        final String query = "SELECT user_id, role_id FROM users_roles";
        return jdbcTemplate.query(query, mapApplicationUserRole());
    }

    private RowMapper<ApplicationUserRole> mapApplicationUserRole() {
        return (resultSet, i) -> {
            final Long userId = resultSet.getLong("user_id");
            final Long roleId = resultSet.getLong("role_id");
            return new ApplicationUserRole(userId, roleId);
        };
    }

    private RowMapper<Role> mapRolesFromDatabase() {
        return (resultSet, i) -> {
            final Long id = resultSet.getLong("id");
            final String name = resultSet.getString("name");
            return new Role(id, name);
        };
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
        final User appUser = getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername())).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));

        final Map<Long, String> roles = getApplicationRoles().stream()
                .collect(Collectors.toMap(Role::getId, Role::getName));

        final Set<SimpleGrantedAuthority> authorities =
                getApplicationUserRole().stream()
                        .filter(user -> user.getUserId().equals(appUser.getId()))
                        .map(user -> new SimpleGrantedAuthority(roles.get(user.getRoleId())))
                        .collect(Collectors.toSet());

        return Optional.of(new ApplicationUser(appUser, authorities));
    }
}
