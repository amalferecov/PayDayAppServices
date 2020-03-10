package com.heisenberg.paydayloanrequest.db;

import com.heisenberg.paydayloanrequest.db.entity.NewLoanRequest;
import com.heisenberg.paydayloanrequest.db.repo.LoanRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    LoanRequestRepo loanRequestRepo;

    @Override
    public NewLoanRequest save(NewLoanRequest newLoanRequest) {
        newLoanRequest = loanRequestRepo.save(newLoanRequest);
        return newLoanRequest;
    }

    @Override
    public NewLoanRequest delete(NewLoanRequest newLoanRequest) {
        return null;
    }

    @Override
    public List<NewLoanRequest> getLoanDetailsByNationalID(String nationalID) {
        return loanRequestRepo.findByNationalIDAndIsActive(nationalID,1);
    }
}
