package com.heisenberg.paydayuserdetails.api.userDetailsServices.services;

import com.heisenberg.paydayuserdetails.db.DatabaseService;
import com.heisenberg.paydayuserdetails.db.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CRUDservices {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DatabaseService databaseService;

    public ResponseEntity<UserDetails> save(UserDetails userDetails){
        try {
            logger.info("Trying to save userDetails : {}",userDetails);
            userDetails = databaseService.save(userDetails);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        }catch (Exception e){
            logger.error("save -> e : {}",e,e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserDetails> getByUsername(String username){
        try{
            logger.info("{} : {}","Getting user details by username",username);
            UserDetails user = databaseService.getUserDetailsByUsername(username);
            if(user==null){
                logger.info("{} : {}","user not found",username);
                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
            }
            logger.info("{} : {}","found user",user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            logger.error("get by username -> e : {}",e,e);
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<UserDetails> getByNationalID(String nationalID){
        try{
            logger.info("{} : {}","Trying to get user details by national id",nationalID);
            UserDetails user = databaseService.getUserDetailsByNationalID(nationalID);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            logger.error("get by national id -> e : {}",e,e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
