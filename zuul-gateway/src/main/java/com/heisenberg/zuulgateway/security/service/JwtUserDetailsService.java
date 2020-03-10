package com.heisenberg.zuulgateway.security.service;

import com.heisenberg.ibaruserservice.api.userinfo.internal.UserLoginDetails;
import com.heisenberg.zuulgateway.proxy.LdapServiceProxy;
import com.heisenberg.zuulgateway.proxy.UserDetailsProxy;
import com.heisenberg.zuulgateway.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author heisenberg
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsProxy service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ResponseEntity<com.heisenberg.ibaruserservice.db.entity.UserDetails> response = service.getByUsername(username);
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
