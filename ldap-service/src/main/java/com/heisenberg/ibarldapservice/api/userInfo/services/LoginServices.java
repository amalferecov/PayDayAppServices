package com.heisenberg.ibarldapservice.api.userInfo.services;

import com.heisenberg.ibarldapservice.api.userInfo.internal.UserLoginDetails;
import com.heisenberg.ibarldapservice.proxy.UserDetailsProxy;
import com.heisenberg.ibaruserservice.db.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServices {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    final
    UserDetailsProxy proxy;

    public LoginServices(UserDetailsProxy proxy) {
        this.proxy = proxy;
    }

    public ResponseEntity<UserDetails> getUserDetails(UserLoginDetails userLoginDetails) {

        System.out.println("user login details ******   "+userLoginDetails.toString());
        UserDetails data = getUser(userLoginDetails);

        if(data==null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(data,HttpStatus.OK);
        }
    }



    private UserDetails getUser(UserLoginDetails userLoginDetails){
        logger.info("{} : {}","trying to get userDetails from proxy",userLoginDetails);

        ResponseEntity<UserDetails> response = proxy.getByUsername(userLoginDetails.getUsername());
        if(response.getStatusCodeValue()==200){
            return response.getBody();
        }

        logger.info("{} : {}","user details not found",userLoginDetails);
        return null;
    }




}
