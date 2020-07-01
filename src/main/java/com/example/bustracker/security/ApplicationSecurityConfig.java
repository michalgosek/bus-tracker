package com.example.bustracker.security;

import com.example.bustracker.authentication.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.bustracker.security.ApplicationUserRole.*;


@EnableWebSecurity(debug = true)
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService applicationUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder) {
        this.applicationUserService = applicationUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/*", "/js/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/", "/index", "/streets/**", "/stops/**").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.toString())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .defaultSuccessUrl("/dashboard", true)
                .and()
                .logout()
                    .invalidateHttpSession(true)  //set invalidation state when logout
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/index");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }
}
