package com.jcastillo.jumbo.sandbox.locator.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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


    @PostMapping(value="/login",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AuthenticationResponse login(@RequestBody User user) throws BadRequestException{
    	LOG.info("AuthenticationResponse");
    	if(user==null) {
    		
    		throw new BadRequestException(ErrorCode.EMPTY_CREDENTIALS);
    	}
    	
    	if(user.getUserName()==null||user.getUserName().isBlank()) {
    		throw new BadRequestException(ErrorCode.INVALID_USER);
    	}
    	
    	if(user.getPassword()==null||user.getPassword().isBlank()) {
    		throw new BadRequestException(ErrorCode.INVALID_PASSWORD);
    	}
    	
    	LOG.info("Valid user values");

        try {
            var upt = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
            am.authenticate(upt);
        } catch (BadCredentialsException e) {
        	LOG.warn("Invalid user credentials");
            throw new BadRequestException(ErrorCode.INVALID_CREDENTIALS);

        }

        var userDetails = duds.loadUserByUsername(user.getUserName());


        return new AuthenticationResponse(jwt.generateToken(userDetails));


    }

}