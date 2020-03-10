package com.heisenberg.ibarldapservice.api.userInfo;

import com.heisenberg.ibarldapservice.api.userInfo.internal.UserLoginDetails;
import com.heisenberg.ibarldapservice.api.userInfo.services.LoginServices;
import com.heisenberg.ibaruserservice.db.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {


    @Autowired
    LoginServices loginServices;

    @PostMapping("/info")
    public ResponseEntity<UserDetails> getInfo(@RequestBody  UserLoginDetails userLoginDetails){
        return loginServices.getUserDetails(userLoginDetails);
    }

}
