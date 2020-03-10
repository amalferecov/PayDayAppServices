package com.heisenberg.zuulgateway.security.service;

import com.heisenberg.ibaruserservice.api.userinfo.internal.UserLoginDetails;
import com.heisenberg.zuulgateway.proxy.LdapServiceProxy;
import com.heisenberg.zuulgateway.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LdapUserDetailsService {

    @Autowired
    LdapServiceProxy ldapServiceProxy;

    public UserDetails loadUserFromLDAP(String username, String password) throws UsernameNotFoundException {
        ResponseEntity<com.heisenberg.ibaruserservice.db.entity.UserDetails> response = ldapServiceProxy.getInfo(new UserLoginDetails(username, password));
        if(response.getStatusCodeValue()==200){
            com.heisenberg.ibaruserservice.db.entity.UserDetails user = response.getBody();
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return JwtUserFactory.create(user);
            }

        }else{
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }

    }
}
