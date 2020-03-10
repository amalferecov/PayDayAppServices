package com.heisenberg.paydayuserdetails.db.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import java.time.LocalDate;

@Data
public class UserDetails {
    @Id
    private String nationalID;
    private String name;
    private String surname;
    private String address;
    private int age;
    private String username;
    private String password;
    private String email;
    private LocalDate lastPasswordResetDate;
    private Boolean enabled;

}
