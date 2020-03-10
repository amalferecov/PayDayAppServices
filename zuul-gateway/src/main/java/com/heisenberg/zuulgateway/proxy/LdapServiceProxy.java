package com.heisenberg.zuulgateway.proxy;

import com.heisenberg.ibaruserservice.api.userinfo.internal.UserLoginDetails;
import com.heisenberg.ibaruserservice.db.entity.UserDetails;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ibar-ldap-service")
@RibbonClient(name = "ibar-ldap-service")
public interface LdapServiceProxy {


    @PostMapping("/user/info")
    ResponseEntity<UserDetails> getInfo(@RequestBody UserLoginDetails userLoginDetails);

}
