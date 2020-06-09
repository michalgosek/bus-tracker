package com.example.bustracker.authentication;

import com.example.bustracker.persistence.dao.ApplicationUserDAO;
import com.example.bustracker.persistence.model.Roles;
import com.example.bustracker.persistence.model.User;
import com.example.bustracker.persistence.model.UserRole;
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

    private List<Roles> getApplicationRoles(){
        final String query = "SELECT id, name FROM roles";
        return jdbcTemplate.query(query, mapRolesFromDatabase());
    }

    private List<UserRole> getApplicationUserRole(){
        final String query = "SELECT user_id, role_id FROM users_roles";
        return jdbcTemplate.query(query, mapApplicationUserRole());
    }

    private RowMapper<UserRole> mapApplicationUserRole(){
        return (resultSet, i) -> {
            final Long userId = resultSet.getLong("user_id");
            final Long roleId = resultSet.getLong("role_id");
            return new UserRole(userId, roleId);
        };
    }

    private RowMapper<Roles> mapRolesFromDatabase(){
        return (resultSet, i) -> {
            final Long id = resultSet.getLong("id");
            final String name = resultSet.getString("name");
            return new Roles(id, name);
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
        final Optional<User> appUser = getApplicationUsers()
                .stream().filter(user -> username.equals(user.getUsername())).findFirst();

        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        final Map<Long, String> roles = getApplicationRoles().stream().collect(Collectors.toMap(
            Roles::getId, Roles::getName));

        final Set<UserRole> appUserRole = getApplicationUserRole()
                .stream().filter(userRole -> appUser.get().getId().equals(userRole.getUserId()))
                .collect(Collectors.toSet());

        final Set<SimpleGrantedAuthority> authorities =
                getApplicationUserRole().stream()
                .map(userRole -> new SimpleGrantedAuthority(roles.get(userRole.getRoleId())))
                .collect(Collectors.toSet());

        Optional<ApplicationUser> applicationUser = Optional.of(new ApplicationUser(appUser.get(), authorities));
        return applicationUser;
    }
}
