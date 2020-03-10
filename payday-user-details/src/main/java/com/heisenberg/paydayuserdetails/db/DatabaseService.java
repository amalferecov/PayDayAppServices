package com.heisenberg.paydayuserdetails.db;

import com.heisenberg.paydayuserdetails.db.entity.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface DatabaseService {

    UserDetails save(UserDetails userDetails);

    UserDetails delete(UserDetails userDetails);

    UserDetails getUserDetailsByNationalID(String nationalID);

    UserDetails getUserDetailsByUsername(String username);
}
