package com.jcastillo.jumbo.sandbox.locator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.model.StoreRepository;
import com.jcastillo.jumbo.sandbox.locator.security.User;
import com.jcastillo.jumbo.sandbox.locator.service.StoreDistance;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocatorApplicationTests {
	
	@LocalServerPort
    int randomServerPort;
	
	@Autowired
	StoreRepository storeRep;
	

	/*  @Autowired
	  private TestRestTemplate restTemplate*/
    
	@BeforeAll
    public void populateStoreDB() {
    	  var store1 = new Store();
          store1.setAddressName("Jumbo 's Gravendeel Gravendeel Centrum");
          store1.setLatitude(51.778461);
          store1.setLongitude(4.615551);
          store1.setCity("s Gravendeel");
          store1.setStreet("Kerkstraat");
          store1.setStreet2("37");
          store1.setStreet3("");
          store1.setTodayOpen("08:00");
          store1.setTodayClose("20:00");
          
          storeRep.save(store1);

          var store2 = new Store();
          store2.setAddressName("Jumbo 's-Heerenberg Stadsplein");
          store2.setLatitude(51.874272);
          store2.setLongitude(6.245829);
          store2.setCity("'s-Heerenberg");
          store2.setStreet("Stadsplein");
          store2.setStreet2("71");
          store2.setStreet3("");
          store2.setTodayOpen("08:00");
          store2.setTodayClose("20:00");
          storeRep.save(store2);

          var store3 = new Store();
          store3.setAddressName("Jumbo Aalsmeer Ophelialaan");
          store3.setLatitude(52.264417);
          store3.setLongitude(4.762433);
          store3.setCity("Aalsmeer");
          store3.setStreet("Ophelialaan");
          store3.setStreet2("124");
          store3.setStreet3("");
          store3.setTodayOpen("08:00");
          store3.setTodayClose("20:00");
          storeRep.save(store3);

          var store4 = new Store();
          store4.setAddressName("Jumbo Aalst Paul en Marjon Houben");
          store4.setLatitude(51.399843);
          store4.setLongitude(5.469597);
          store4.setCity("Aalst");
          store4.setStreet("Hortensialaan");
          store4.setStreet2("2");
          store4.setStreet3("");
          store4.setTodayOpen("08:00");
          store4.setTodayClose("20:00");
          storeRep.save(store4);

          var store5 = new Store();
          store5.setAddressName("Jumbo Aalten Leussink");
          store5.setLatitude(51.923993);
          store5.setLongitude(6.576066);
          store5.setCity("Aardenburg");
          store5.setStreet("Peurssensstraat");
          store5.setStreet2("21");
          store5.setStreet3("");
          store5.setTodayOpen("08:00");
          store5.setTodayClose("20:00");        
          storeRep.save(store5);

          var store6 = new Store();
          store6.setAddressName("Jumbo Aardenburg Ingels");
          store6.setLatitude(51.275006);
          store6.setLongitude(3.444601);
          store6.setCity("Aalten");
          store6.setStreet("Admiraal de Ruyterstraat");
          store6.setStreet2("10");
          store6.setStreet3("");
          store6.setTodayOpen("08:00");
          store6.setTodayClose("20:00");  
          storeRep.save(store6);

          var store7 = new Store();
          store7.setAddressName("Jumbo Alkmaar Duijvelshoff");
          store7.setLatitude(52.645601);
          store7.setLongitude(4.749492);
          store7.setCity("Alkmaar");
          store7.setStreet("Muiderwaard");
          store7.setStreet2("416");
          store7.setStreet3("");
          store7.setTodayOpen("08:00");
          store7.setTodayClose("20:00"); 
          storeRep.save(store7);

          var store8 = new Store();
          store8.setAddressName("Jumbo Vlijmen Oliemaat");
          store8.setLatitude(51.694559);
          store8.setLongitude(5.221231);
          store8.setCity("Vlijmen");
          store8.setStreet("Oliemaat");
          store8.setStreet2("4-5");
          store8.setStreet3("");
          store8.setTodayOpen("08:00");
          store8.setTodayClose("20:00");        
          storeRep.save(store8);

          var store9 = new Store();
          store9.setAddressName("Jumbo Alkmaar J. Naberstraat");
          store9.setLatitude(52.665822);
          store9.setLongitude(4.766146);
          store9.setCity("Alkmaar");
          store9.setStreet("J. Naberstraat");
          store9.setStreet2("49-51");
          store9.setStreet3("");
          store9.setTodayOpen("08:00");
          store9.setTodayClose("20:00");  
          storeRep.save(store9);

          var store10 = new Store();
          store10.setAddressName("Jumbo Alkmaar Paardenmarkt");
          store10.setLatitude(52.633740);
          store10.setLongitude(4.745031);
          store10.setCity("Paardenmarkt");
          store10.setStreet("Oliemaat");
          store10.setStreet2("2");
          store10.setStreet3("");
          store10.setTodayOpen("08:00");
          store10.setTodayClose("20:00"); 
          storeRep.save(store10);

          var store11 = new Store();
          store11.setAddressName("Jumbo Alkmaar Winkelwaard");
          store11.setLatitude(52.645642);
          store11.setLongitude(4.759373);
          store11.setCity("Alkmaar");
          store11.setStreet("Winkelwaard");
          store11.setStreet2("486");
          store11.setStreet3("");
          store11.setTodayOpen("08:00");
          store11.setTodayClose("22:00"); 
          storeRep.save(store11);

          var store12 = new Store();
          store12.setAddressName("Jumbo Almelo Bornerbroeksestraat");
          store12.setLatitude(52.349813);
          store12.setLongitude(6.660543);
          store12.setCity("Almelo");
          store12.setStreet("Bornerbroeksestraat");
          store12.setStreet2("79");
          store12.setStreet3("");
          store12.setTodayOpen("08:00");
          store12.setTodayClose("22:00"); 
          storeRep.save(store12);


          var store13 = new Store();
          store13.setAddressName("Jumbo Almere Waterwijk Geinplein");
          store13.setLatitude(52.384906);
          store13.setLongitude(5.224110);
          store13.setCity("Almelo");
          store13.setStreet("Bornerbroeksestraat");
          store13.setStreet2("79");
          store13.setStreet3("");
          store13.setTodayOpen("08:00");
          store13.setTodayClose("22:00"); 
          storeRep.save(store13);

          var store14 = new Store();
          store14.setAddressName("Jumbo Almere-Buiten Detroitpad");
          store14.setLatitude(52.395474);
          store14.setLongitude(5.274883);
          store14.setCity("Almelo");
          store14.setStreet("Bornerbroeksestraat");
          store14.setStreet2("79");
          store14.setStreet3("");
          store14.setTodayOpen("08:00");
          store14.setTodayClose("22:00"); 
          storeRep.save(store14);

          var store15 = new Store();
          store15.setAddressName("Jumbo Almere-Haven Jaagmeent");
          store15.setLatitude(52.336693);
          store15.setLongitude(5.231883);
          store15.setCity("Almelo");
          store15.setStreet("Bornerbroeksestraat");
          store15.setStreet2("79");
          store15.setStreet3("");
          store15.setTodayOpen("08:00");
          store15.setTodayClose("22:00"); 
          storeRep.save(store15);

          var store16 = new Store();
          store16.setAddressName("Jumbo Alphen aan den Rijn Ten Brink Food");
          store16.setLatitude(52.150632);
          store16.setLongitude(4.661277);
          store16.setCity("Almelo");
          store16.setStreet("Bornerbroeksestraat");
          store16.setStreet2("79");
          store16.setStreet3("");
          store16.setTodayOpen("08:00");
          store16.setTodayClose("22:00"); 
          storeRep.save(store16);

          var store17 = new Store();
          store17.setAddressName("Jumbo Amerongen Koenestraat");
          store17.setLatitude(52.002437);
          store17.setLongitude(5.461122);
          store17.setCity("Almelo");
          store17.setStreet("Bornerbroeksestraat");
          store17.setStreet2("79");
          store17.setStreet3("");
          store17.setTodayOpen("08:00");
          store17.setTodayClose("22:00"); 
          storeRep.save(store17);

          var store18 = new Store();
          store18.setAddressName("Jumbo Amersfoort Den Blanken Emiclaer");
          store18.setLatitude(52.185990);
          store18.setLongitude(5.398059);
          store18.setCity("Almelo");
          store18.setStreet("Bornerbroeksestraat");
          store18.setStreet2("79");
          store18.setStreet3("");
          store18.setTodayOpen("08:00");
          store18.setTodayClose("22:00"); 
          storeRep.save(store18);


          var store19 = new Store();
          store19.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
          store19.setLatitude(52.199279);
          store19.setLongitude(5.376590);
          store19.setCity("Almelo");
          store19.setStreet("Bornerbroeksestraat");
          store19.setStreet2("79");
          store19.setStreet3("");
          store19.setTodayOpen("08:00");
          store19.setTodayClose("22:00"); 
          storeRep.save(store19);

          var store20 = new Store();
          store20.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
          store20.setLatitude(52.199279);
          store20.setLongitude(5.376590);
          store20.setCity("Almelo");
          store20.setStreet("Bornerbroeksestraat");
          store20.setStreet2("79");
          store20.setStreet3("");
          store20.setTodayOpen("08:00");
          store20.setTodayClose("22:00"); 
          storeRep.save(store20);

          var store21 = new Store();
          store21.setAddressName("Jumbo Amersfoort Leusderweg.");
          store21.setLatitude(52.143866);
          store21.setLongitude(5.381369);
          store21.setCity("Almelo");
          store21.setStreet("Bornerbroeksestraat");
          store21.setStreet2("79");
          store21.setStreet3("");
          store21.setTodayOpen("08:00");
          store21.setTodayClose("22:00"); 
          storeRep.save(store21);

          var store22 = new Store();
          store22.setAddressName("Jumbo Amersfoort Neptunusplein");
          store22.setLatitude(52.162897);
          store22.setLongitude(5.399515);
          store22.setCity("Almelo");
          store22.setStreet("Bornerbroeksestraat");
          store22.setStreet2("79");
          store22.setStreet3("");
          store22.setTodayOpen("08:00");
          store22.setTodayClose("22:00"); 
          storeRep.save(store22);

          var store23 = new Store();
          store23.setAddressName("Jumbo Amersfoort PUP Medewerkers FrieslandCampina");
          store23.setLatitude(52.151843);
          store23.setLongitude(5.377845);
          store23.setCity("Almelo");
          store23.setStreet("Bornerbroeksestraat");
          store23.setStreet2("79");
          store23.setStreet3("");
          store23.setTodayOpen("08:00");
          store23.setTodayClose("22:00"); 
          storeRep.save(store23);

          var store24 = new Store();
          store24.setAddressName("Jumbo Amersfoort Pieter Stastokerf");
          store24.setLatitude(52.173049);
          store24.setLongitude(5.389766);
          store24.setCity("Almelo");
          store24.setStreet("Bornerbroeksestraat");
          store24.setStreet2("79");
          store24.setStreet3("");
          store24.setTodayOpen("08:00");
          store24.setTodayClose("22:00"); 
          storeRep.save(store24);

          var store25 = new Store();
          store25.setAddressName("Jumbo Amersfoort Vathorst");
          store25.setLatitude(52.193992);
          store25.setLongitude(5.430602);
          store25.setCity("Almelo");
          store25.setStreet("Bornerbroeksestraat");
          store25.setStreet2("79");
          store25.setStreet3("");
          store25.setTodayOpen("08:00");
          store25.setTodayClose("22:00"); 
          storeRep.save(store25);

          var store30 = new Store();
          store30.setAddressName("Jumbo Amersfoort Wiekslag");
          store30.setLatitude(52.166540);
          store30.setLongitude(5.409000);
          store30.setCity("Almelo");
          store30.setStreet("Bornerbroeksestraat");
          store30.setStreet2("79");
          store30.setStreet3("");
          store30.setTodayOpen("08:00");
          store30.setTodayClose("22:00"); 
          storeRep.save(store30);
    	
    }
   
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
        final String closestUrl ="http://localhost:"+randomServerPort+"/api/v1/stores/nearbystores?latitude=51.923993&longitude=6.576066";
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
        final String closestUrl ="http://localhost:"+randomServerPort+"/api/v1/stores/nearbystores?latitude=52.349813&longitude=6.660543";
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
