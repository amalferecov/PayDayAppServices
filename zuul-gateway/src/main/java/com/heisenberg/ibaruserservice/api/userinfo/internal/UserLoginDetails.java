package com.heisenberg.ibaruserservice.api.userinfo.internal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDetails {
    private String username;
    private String password;


    @Override
    public boolean equals(Object o) {
        System.out.println("step 1");
        if (this == o) return true;
        System.out.println("step 2");
        if (o == null || getClass() != o.getClass()) return false;
        System.out.println("step 3");
        UserLoginDetails that = (UserLoginDetails) o;
        System.out.println("step 4");
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
