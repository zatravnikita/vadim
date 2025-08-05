package com.example.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsersService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public UsersService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return jdbcTemplate.queryForObject(
                    "SELECT id FROM users WHERE login = ?",
                    Integer.class,
                    username
            );

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jdbcTemplate.queryForObject(
                "SELECT login, password, authority FROM users WHERE login = ?",
                (rs, rowNum) -> new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        Collections.singleton(new SimpleGrantedAuthority(rs.getString("authority")))
                ),
                username
        );
    }
}