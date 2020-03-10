package com.heisenberg.zuulgateway.controller;

import com.heisenberg.zuulgateway.security.JwtAuthenticationRequest;
import com.heisenberg.zuulgateway.security.JwtTokenUtil;
import com.heisenberg.zuulgateway.security.service.JwtAuthenticationResponse;
import com.heisenberg.zuulgateway.security.service.JwtUserDetailsService;
import com.heisenberg.zuulgateway.security.service.LdapUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
public class AuthenticationRestController {

    @Value("${jwt.header")
    private String tokenHeader;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }
//
//    @Autowired
//    private LdapUserDetailsService ldapUserDetailsService;

//    @Autowired
//    @Qualifier("jwtUserDetailsService")
//    private UserDetailsService userDetailsService;

    /**
     * Reload password post-security so we can generate the token and then send it back
     * @param authenticationRequest
     * @return ResponseEntity with token for user
     * @throws AuthenticationException
     */
//    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        System.out.println("username : "+authenticationRequest.getUsername());
        System.out.println("password : "+authenticationRequest.getPassword());
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        //
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }


    /* Generating Bcrypt Password as per Spring Security
        	 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		String hashedPassword = passwordEncoder.encode("userpass");*/
    /**
     *
     * Authenticates the user.
     * If something is wrong,
     * an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}
