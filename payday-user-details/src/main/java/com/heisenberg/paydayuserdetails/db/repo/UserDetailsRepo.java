package com.heisenberg.paydayuserdetails.db.repo;

import com.heisenberg.paydayuserdetails.db.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserDetailsRepo extends MongoRepository<UserDetails,String> {
    public UserDetails findByUsername(String username);
    public UserDetails findByNationalID(String nationalID);
}
