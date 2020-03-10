package com.heisenberg.zuulgateway.proxy;


import com.heisenberg.ibaruserservice.db.entity.UserDetails;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payday-user-details")
@RibbonClient(name = "payday-user-details")
public interface UserDetailsProxy {

    @GetMapping("/user/get/username/{username}")
    ResponseEntity<UserDetails> getByUsername(@PathVariable String username);
}
