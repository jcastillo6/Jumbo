package com.jcastillo.jumbo.sandbox.locator.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcastillo.jumbo.sandbox.locator.security.AuthenticationResponse;
import com.jcastillo.jumbo.sandbox.locator.security.DefaultUserDetailService;
import com.jcastillo.jumbo.sandbox.locator.security.JwsUtil;
import com.jcastillo.jumbo.sandbox.locator.security.User;


/**
 * Security services, login and other related methods
 * @author jorge castillo
 *
 */
@RestController
@RequestMapping("/api/v1")
public class Access {
	
	private final Logger LOG = org.slf4j.LoggerFactory.getLogger(Access.class);

    @Autowired
    private AuthenticationManager am;

    @Autowired
    private DefaultUserDetailService duds;

    @Autowired
    private JwsUtil jwt;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User user) throws BadRequestException{
    	LOG.info("AuthenticationResponse");
    	if(user==null) {
    		
    		throw new BadRequestException("Invalid credentials");
    	}
    	
    	if(user.getUserName()==null||user.getUserName().isBlank()) {
    		throw new BadRequestException("Invalid user");
    	}
    	
    	if(user.getPassword()==null||user.getPassword().isBlank()) {
    		throw new BadRequestException("Invalid password");
    	}
    	
    	LOG.info("Valid values");

        try {
            var upt = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
            am.authenticate(upt);
        } catch (BadCredentialsException e) {
        	LOG.warn("Invalid credentials");
            throw new BadRequestException("Incorrect user name or password");

        }

        var userDetails = duds.loadUserByUsername(user.getUserName());


        return new AuthenticationResponse(jwt.generateToken(userDetails));


    }

}