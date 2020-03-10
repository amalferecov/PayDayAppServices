package com.heisenberg.zuulgateway.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public class JwtUser implements UserDetails {
    private final Long id;
    private final String nationalID;
    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final LocalDate lastPasswordResetDate;


    public JwtUser(
            Long id,
            String nationalID,
            String name,
            String surname,
            String email,
            String username,
            String password, Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            LocalDate lastPasswordResetDate
    ) {
        this.id = id;
        this.nationalID = nationalID;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getNationalID(){
        return nationalID;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }
    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public LocalDate getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
