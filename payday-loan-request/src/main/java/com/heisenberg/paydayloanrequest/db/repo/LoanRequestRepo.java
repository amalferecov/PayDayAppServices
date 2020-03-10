package com.heisenberg.paydayloanrequest.db.repo;

import com.heisenberg.paydayloanrequest.db.entity.NewLoanRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LoanRequestRepo extends MongoRepository<NewLoanRequest,String> {
    List<NewLoanRequest> findByNationalIDAndIsActive(String nationalID, int isActive);
//    NewLoanRequest findByNationalID(String nationalID);
}
