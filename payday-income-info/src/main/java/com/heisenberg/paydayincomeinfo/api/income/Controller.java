package com.heisenberg.paydayincomeinfo.api.income;

import com.heisenberg.paydayincomeinfo.api.income.internal.Income;
import com.heisenberg.paydayincomeinfo.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/income")
public class Controller {

    @Autowired
    IncomeService service;

    @GetMapping("/get-last/{nationalID}")
    public ResponseEntity<List<Income>> gettingLastYearIncome(@PathVariable String nationalID){
        return service.getLastYearIncome(nationalID);
    }
}
