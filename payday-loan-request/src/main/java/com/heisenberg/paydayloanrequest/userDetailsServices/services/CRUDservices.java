package com.heisenberg.paydayloanrequest.userDetailsServices.services;

import com.heisenberg.paydayloanrequest.db.DatabaseService;
import com.heisenberg.paydayloanrequest.db.entity.NewLoanRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CRUDservices {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DatabaseService databaseService;

    public ResponseEntity<NewLoanRequest> save(NewLoanRequest newLoanRequest){
        try {
            logger.info("Trying to save newLoan request : {}",newLoanRequest);
            initRequestLoan(newLoanRequest);
            newLoanRequest = databaseService.save(newLoanRequest);
            return new ResponseEntity<>(newLoanRequest, HttpStatus.OK);
        }catch (Exception e){
            logger.error("save -> e : {}",e,e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void initRequestLoan(NewLoanRequest newLoanRequest){
        newLoanRequest.setId(newLoanRequest.getNationalID().concat("_").concat(LocalDateTime.now().toString()));
        newLoanRequest.setIsActive(1);
        newLoanRequest.setRequestDate(LocalDate.now());
        newLoanRequest.setStatus(0);
    }

    public ResponseEntity<List<NewLoanRequest>> getByNationalID(String nationalID){
        try{
            logger.info("{} : {}","Trying to get user loan detial by national id",nationalID);
            List<NewLoanRequest> list = databaseService.getLoanDetailsByNationalID(nationalID);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            logger.error("get by national id -> e : {}",e,e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
