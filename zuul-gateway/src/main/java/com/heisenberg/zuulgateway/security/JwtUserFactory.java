package com.heisenberg.zuulgateway.security;

import com.heisenberg.ibaruserservice.db.entity.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserDetails user) {
        List<String> authorityList = new ArrayList<>();
        authorityList.add("user_roll");

        return new JwtUser(
                user.getId(),
                user.getNationalID(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(authorityList),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
