package com.heisenberg.paydayloanrequest.db;

import com.heisenberg.paydayloanrequest.db.entity.NewLoanRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DatabaseService {

    NewLoanRequest save(NewLoanRequest newLoanRequest);

    NewLoanRequest delete(NewLoanRequest newLoanRequest);

    List<NewLoanRequest> getLoanDetailsByNationalID(String nationalID);

}
