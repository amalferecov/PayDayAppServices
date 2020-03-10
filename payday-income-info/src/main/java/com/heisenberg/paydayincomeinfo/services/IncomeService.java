package com.heisenberg.paydayincomeinfo.services;

import com.heisenberg.paydayincomeinfo.api.income.internal.Income;
import com.heisenberg.paydayincomeinfo.cache.RedisMapKey;
import com.heisenberg.paydayincomeinfo.cache.RedisService;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisService redisService;

    public ResponseEntity<List<Income>> getLastYearIncome(String nationalID){
        List<Income> listOfMonthlyIncome;
        listOfMonthlyIncome = getIncomeForUserFromRedis(nationalID);
        if(!checkForIncome(nationalID,listOfMonthlyIncome)){
            //first time sending request to service getting user income
            //rest request or proxy will be added here
            //this is for test
            logger.info("{} : {}","starting to create new list for test",nationalID);
            listOfMonthlyIncome = initIncomeForTest(nationalID);
        }

        if(listOfMonthlyIncome==null||listOfMonthlyIncome.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(listOfMonthlyIncome,HttpStatus.OK);
        }

    }

    private boolean checkForIncome(String nationalID, List<Income> listOfMonthlyIncome) {
        if(listOfMonthlyIncome==null||listOfMonthlyIncome.isEmpty()){
            logger.info("{} : {}","checking for income","list not found from redis");
            return false;
        }

        //checking list for last month data
        if(checkForLastMonthIncome(listOfMonthlyIncome)){
            logger.info("{} : {}","checking last month","last month found");
            return true;
        }


        return false;

    }

    private boolean checkForLastMonthIncome(List<Income> list){
//        list.stream().filter(income -> income.getIncomeDate().getMonth() == LocalDate.now().getMonth());
        for (Income income : list) {
            if(income.getIncomeDate().getMonth()==LocalDate.now().getMonth().minus(1)){
                return true;
            }
        }

        return false;
    }

    /**
     * This method just for test.
     * @param nationalID
     * @return
     */
    private List<Income> initIncomeForTest(String nationalID) {
        logger.info("{} : {}","test init method","creating list");
        List<Income> list = new ArrayList<>();
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2020-02-20")));
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2020-03-20")));
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2020-01-20")));
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2019-12-20")));
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2019-11-20")));
        list.add(new Income(new BigDecimal(1200), LocalDate.parse("2019-10-20")));

        logger.info("{} : {}","adding list to redis",nationalID);
        redisService.add(nationalID,list,RedisMapKey.MAP_OF_INCOME);

        return list;
    }

    private List<Income> getIncomeForUserFromRedis(String nationalID){
        logger.info("{} : {}","trying to get list from redis for national id",nationalID);
        return redisService.get(nationalID, RedisMapKey.MAP_OF_INCOME);
    }
}
