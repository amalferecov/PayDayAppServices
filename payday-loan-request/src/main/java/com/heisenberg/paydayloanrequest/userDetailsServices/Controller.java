package com.heisenberg.paydayloanrequest.userDetailsServices;

import com.heisenberg.paydayloanrequest.db.entity.NewLoanRequest;
import com.heisenberg.paydayloanrequest.userDetailsServices.services.CRUDservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class Controller {

    @Autowired
    CRUDservices cruDservices;


    @PostMapping("/new")
    public ResponseEntity<NewLoanRequest> save(@RequestBody NewLoanRequest loanRequest){
        return cruDservices.save(loanRequest);
    }

    @GetMapping("/get/{nationalID}")
    public ResponseEntity<List<NewLoanRequest>> get(@PathVariable String nationalID){
        return cruDservices.getByNationalID(nationalID);
    }


}
