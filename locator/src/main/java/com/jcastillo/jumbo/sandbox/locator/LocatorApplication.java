package com.jcastillo.jumbo.sandbox.locator;

import com.jcastillo.jumbo.sandbox.locator.entity.Store;
import com.jcastillo.jumbo.sandbox.locator.model.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocatorApplication implements CommandLineRunner {
    @Autowired
    private StoreRepository storeRep;

    public static void main(String[] args) {
        SpringApplication.run(LocatorApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        var store1 = new Store();
        store1.setAddressName("Jumbo 's Gravendeel Gravendeel Centrum");
        store1.setLatitude(51.778461);
        store1.setLongitude(4.615551);
        storeRep.save(store1);

        var store2 = new Store();
        store2.setAddressName("Jumbo 's-Heerenberg Stadsplein");
        store2.setLatitude(51.874272);
        store2.setLongitude(6.245829);
        storeRep.save(store2);

        var store3 = new Store();
        store3.setAddressName("Jumbo Aalsmeer Ophelialaan");
        store3.setLatitude(52.264417);
        store3.setLongitude(4.762433);
        storeRep.save(store3);

        var store4 = new Store();
        store4.setAddressName("Jumbo Aalst Paul en Marjon Houben");
        store4.setLatitude(51.399843);
        store4.setLongitude(5.469597);
        storeRep.save(store4);

        var store5 = new Store();
        store5.setAddressName("Jumbo Aalten Leussink");
        store5.setLatitude(51.923993);
        store5.setLongitude(6.576066);
        storeRep.save(store5);

        var store6 = new Store();
        store6.setAddressName("Jumbo Aardenburg Ingels");
        store6.setLatitude(51.275006);
        store6.setLongitude(3.444601);
        storeRep.save(store6);

        var store7 = new Store();
        store7.setAddressName("Jumbo Alkmaar Duijvelshoff");
        store7.setLatitude(52.645601);
        store7.setLongitude(4.749492);
        storeRep.save(store7);

        var store8 = new Store();
        store8.setAddressName("Jumbo Alkmaar Duijvelshoff");
        store8.setLatitude(52.645601);
        store8.setLongitude(4.749492);
        storeRep.save(store8);

        var store9 = new Store();
        store9.setAddressName("Jumbo Alkmaar J. Naberstraat");
        store9.setLatitude(52.665822);
        store9.setLongitude(4.766146);
        storeRep.save(store9);

        var store10 = new Store();
        store10.setAddressName("Jumbo Alkmaar Paardenmarkt");
        store10.setLatitude(52.633740);
        store10.setLongitude(4.745031);
        storeRep.save(store10);

        var store11 = new Store();
        store11.setAddressName("Jumbo Alkmaar Winkelwaard");
        store11.setLatitude(52.645642);
        store11.setLongitude(4.759373);
        storeRep.save(store11);

        var store12 = new Store();
        store12.setAddressName("Jumbo Almelo Bornerbroeksestraat");
        store12.setLatitude(52.349813);
        store12.setLongitude(6.660543);
        storeRep.save(store12);


        var store13 = new Store();
        store13.setAddressName("Jumbo Almere Waterwijk Geinplein");
        store13.setLatitude(52.384906);
        store13.setLongitude(5.224110);
        storeRep.save(store13);

        var store14 = new Store();
        store14.setAddressName("Jumbo Almere-Buiten Detroitpad");
        store14.setLatitude(52.395474);
        store14.setLongitude(5.274883);
        storeRep.save(store14);

        var store15 = new Store();
        store15.setAddressName("Jumbo Almere-Haven Jaagmeent");
        store15.setLatitude(52.336693);
        store15.setLongitude(5.231883);
        storeRep.save(store14);

        var store16 = new Store();
        store16.setAddressName("Jumbo Alphen aan den Rijn Ten Brink Food");
        store16.setLatitude(52.150632);
        store16.setLongitude(4.661277);
        storeRep.save(store16);

        var store17 = new Store();
        store17.setAddressName("Jumbo Amerongen Koenestraat");
        store17.setLatitude(52.002437);
        store17.setLongitude(5.461122);
        storeRep.save(store17);

        var store18 = new Store();
        store18.setAddressName("Jumbo Amersfoort Den Blanken Emiclaer");
        store18.setLatitude(52.185990);
        store18.setLongitude(5.398059);
        storeRep.save(store18);


        var store19 = new Store();
        store19.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
        store19.setLatitude(52.199279);
        store19.setLongitude(5.376590);
        storeRep.save(store19);

        var store20 = new Store();
        store20.setAddressName("Jumbo Amersfoort Den Blanken Nieuwland");
        store20.setLatitude(52.199279);
        store20.setLongitude(5.376590);
        storeRep.save(store20);

        var store21 = new Store();
        store21.setAddressName("Jumbo Amersfoort Leusderweg.");
        store21.setLatitude(52.143866);
        store21.setLongitude(5.381369);
        storeRep.save(store21);

        var store22 = new Store();
        store22.setAddressName("Jumbo Amersfoort Neptunusplein");
        store22.setLatitude(52.162897);
        store22.setLongitude(5.399515);
        storeRep.save(store22);

        var store23 = new Store();
        store23.setAddressName("Jumbo Amersfoort PUP Medewerkers FrieslandCampina");
        store23.setLatitude(52.151843);
        store23.setLongitude(5.377845);
        storeRep.save(store23);

        var store24 = new Store();
        store24.setAddressName("Jumbo Amersfoort Pieter Stastokerf");
        store24.setLatitude(52.173049);
        store24.setLongitude(5.389766);
        storeRep.save(store24);

        var store25 = new Store();
        store25.setAddressName("Jumbo Amersfoort Vathorst");
        store25.setLatitude(52.193992);
        store25.setLongitude(5.430602);
        storeRep.save(store25);

        var store30 = new Store();
        store30.setAddressName("Jumbo Amersfoort Wiekslag");
        store30.setLatitude(52.166540);
        store30.setLongitude(5.409000);
        storeRep.save(store30);

    }
}
