package com.heisenberg.paydayuserdetails.api.userDetailsServices;

import com.heisenberg.paydayuserdetails.api.userDetailsServices.services.CRUDservices;
import com.heisenberg.paydayuserdetails.db.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    CRUDservices cruDservices;


    @PostMapping("/save")
    public ResponseEntity<UserDetails> save(@RequestBody UserDetails userDetails){
        return cruDservices.save(userDetails);
    }

    @GetMapping("/get/{nationalID}")
    public ResponseEntity<UserDetails> get(@PathVariable String nationalID){
        return cruDservices.getByNationalID(nationalID);
    }


    @GetMapping("/get/username/{username}")
    public ResponseEntity<UserDetails> getByUsername(@PathVariable String username){
        return cruDservices.getByUsername(username);
    }
}
