package com.heisenberg.paydayuserdetails.db;

import com.heisenberg.paydayuserdetails.db.entity.UserDetails;
import com.heisenberg.paydayuserdetails.db.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails save(UserDetails userDetails) {
        return userDetailsRepo.save(userDetails);
    }

    @Override
    public UserDetails delete(UserDetails userDetails) {
        return null;
    }


    @Override
    public UserDetails getUserDetailsByNationalID(String nationalID) {
        return userDetailsRepo.findByNationalID(nationalID);
    }

    @Override
    public UserDetails getUserDetailsByUsername(String username){
        return userDetailsRepo.findByUsername(username);
    }
}
