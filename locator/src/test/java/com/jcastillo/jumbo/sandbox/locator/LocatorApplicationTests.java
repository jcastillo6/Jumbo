package com.jcastillo.jumbo.sandbox.locator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jcastillo.jumbo.sandbox.locator.controller.StoreDistance;
import com.jcastillo.jumbo.sandbox.locator.distance.Calculator;
import com.jcastillo.jumbo.sandbox.locator.security.User;

import antlr.collections.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
class LocatorApplicationTests {
	
	@LocalServerPort
    int randomServerPort;
    
   
    @Test
    void loginTest() throws URISyntaxException {
    	RestTemplate rst = new RestTemplate();
        final String loginUrl ="http://localhost:"+randomServerPort+"/api/v1/login";
        URI uri = new URI(loginUrl);
        User user = new User("user", "user1");
        ResponseEntity<String> result = rst.postForEntity(uri,user ,String.class);

        assertEquals(200, result.getStatusCodeValue());
            	
    	
    }
    
    @Test
    void loginTest2() throws URISyntaxException {
    	RestTemplate rst = new RestTemplate();
        final String loginUrl ="http://localhost:"+randomServerPort+"/api/v1/login";
        URI uri = new URI(loginUrl);
        User user = new User("sadsad", "dasdsadas");
        String message="";
        try {
        	rst.postForEntity(uri,user ,String.class);
        }
        catch(HttpClientErrorException e) {
        	message=e.getMessage();
        }
        assertTrue(message.contains("400"));
            	
    	
    }
    
    
    @Test
    void loginTest3() throws URISyntaxException {
    	RestTemplate rst = new RestTemplate();
        final String loginUrl ="http://localhost:"+randomServerPort+"/api/v1/login";
        URI uri = new URI(loginUrl);
        User user = new User(null, null);
        String message="";
        try {
        	rst.postForEntity(uri,user ,String.class);
        }
        catch(HttpClientErrorException e) {
        	message=e.getMessage();
        }
        assertTrue(message.contains("400"));
            	
    	
    }
    
    
    @Test
    void storeClosest() throws URISyntaxException {
    	RestTemplate rst = new RestTemplate();
        final String loginUrl ="http://localhost:"+randomServerPort+"/api/v1/login";
        final String closestUrl ="http://localhost:"+randomServerPort+"/api/v1/store/closest?latitude=51.923993&longitude=6.576066";
        URI uri = new URI(loginUrl);
        User user = new User("user", "user1");
        ResponseEntity<String> result=rst.postForEntity(uri,user ,String.class);       
        
        String token=result.getBody().substring(8, result.getBody().length()-2);
        
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);

        //Create a new HttpEntity
        final HttpEntity<StoreDistance[]> entity = new HttpEntity<>(headers);
        
        //Execute the method writing your HttpEntity to the request
        ResponseEntity<StoreDistance[]> response = rst.exchange(closestUrl, HttpMethod.GET, entity, StoreDistance[].class);        
        StoreDistance[] storeDistances = response.getBody();
        
                
        assertTrue(storeDistances[0].getStore().getAddressName().equals("Jumbo Aalten Leussink"));
            	
    	
    }
    
    
    
    @Test
    void storeClosest2() throws URISyntaxException {
    	RestTemplate rst = new RestTemplate();
        final String loginUrl ="http://localhost:"+randomServerPort+"/api/v1/login";
        final String closestUrl ="http://localhost:"+randomServerPort+"/api/v1/store/closest?latitude=52.349813&longitude=6.660543";
        URI uri = new URI(loginUrl);
        User user = new User("user", "user1");
        ResponseEntity<String> result=rst.postForEntity(uri,user ,String.class);       
        
        String token=result.getBody().substring(8, result.getBody().length()-2);
        
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+token);

        //Create a new HttpEntity
        final HttpEntity<StoreDistance[]> entity = new HttpEntity<>(headers);
        
        //Execute the method writing your HttpEntity to the request
        ResponseEntity<StoreDistance[]> response = rst.exchange(closestUrl, HttpMethod.GET, entity, StoreDistance[].class);        
        StoreDistance[] storeDistances = response.getBody();
        
                
        assertTrue(storeDistances[0].getStore().getAddressName().equals("Jumbo Almelo Bornerbroeksestraat"));
            	
    	
    }

}
