package com.heisenberg.ibaruserservice.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private Long id;
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
