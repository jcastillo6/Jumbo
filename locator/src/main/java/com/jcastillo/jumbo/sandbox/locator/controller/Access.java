package com.jcastillo.jumbo.sandbox.locator.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;




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
    @ApiOperation("Login request, it returns a token to get access to the api")
    @ApiResponses(value = { @ApiResponse(responseCode =  "200", description = "Success, return a valid token"),
    		@ApiResponse(responseCode =  "400", description = "Bad request parameter, the service can return a message in the body with the "
    				+ "following information: {errorCode: 1000, message: The credential values are not valid, please check the user and password are requiered},"
    				+ "{errorCode 1001,message: The user name is mandatory}, {errorCode: 1002,message: The password is mandatory}, {errorCode: 1003, message: the user or password are incorrect, please check} ")})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) throws BadRequestException{
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


        return new ResponseEntity<>(new AuthenticationResponse(jwt.generateToken(userDetails)),HttpStatus.OK);


    }

}