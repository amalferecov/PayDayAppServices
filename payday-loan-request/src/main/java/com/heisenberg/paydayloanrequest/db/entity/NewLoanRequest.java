package com.heisenberg.paydayloanrequest.db.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class NewLoanRequest {

    @Id
    private String id;
    private String nationalID;
    private Integer amount;
    private Integer month;
    private LocalDate requestDate;
    private int isActive;
    private int status;//0-waiting, 1-accepted, 2-cancelled

}
