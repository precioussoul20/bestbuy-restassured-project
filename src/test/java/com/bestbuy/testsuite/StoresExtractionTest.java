package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public static void storeExtractTest() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);


        //1. Extract the limit
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is :" + limit);

        //2. Extract the total
        int total = response.extract().path("total");
        System.out.println("The value of total is :" + total);

        //3. Extract the name of 5th store
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th stores is :" + name);

        //4. Extract the names of all the store
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("The names of all stores is :" + allStoreNames);

        //5. Extract the storeId of all the store
        List<Integer> storeIds = response.extract().path("data.id");
        System.out.println("The id of all the stores is :" + storeIds);

        //6. Print the size of the data list
        List<Integer> dataSize = response.extract().path("data.length");
        System.out.println("The size of the data list is :" + dataSize.size());

        //7. Get all the value of the store where store name = St Cloud
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("The value  of the store name 'St Cloud' is :" + value);

        //8. Get the address of the store where store name = Rochester
        String storeAddress = response.extract().path("data[8].address");
        System.out.println("The address of the store name Rochester is :" + storeAddress);

        //9. Get all the services of 8th store
        List<HashMap<String, Objects>> services = response.extract().path("data[7].services");
        System.out.println("All the services of 8th store is :" + services);

        //10. Get storeservices of the store where service name = Windows Store
        List<List<String>> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("Store services of the store where service name = Windows Store : " + storeServices);

        //11. Get all the storeId of all the store
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("All store id of all stores :" + allStoreId);

        //12. Get id of all the store
        List<Integer> idOfAllStores = response.extract().path("data.id");
        System.out.println("Id of all stores : " +idOfAllStores);

        //13. Find the store names Where state = ND
        List<String> storeName = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("Get store name where state = ND :" + storeName);

        //14. Find the Total number of services for the store where store name = Rochester
        List<List<Integer>> totalservices = response.extract().path("data.findAll{it.name=='Rochester'}.services");
        System.out.println("Find the total no of services for store Rochester :" + totalservices.get(0).size());

        //15. Find the createdAt for all services whose name = “Windows Store”
        List<Objects> createdAt = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("Find the createdAt for all services whose store name = 'Windows Store' :" + createdAt);

        //16. Find the name of all services Where store name = “Fargo”
        List<String> allServicesName = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("Get name of all services where store name = 'Fargo' :" + allServicesName);

        //17. Find the zip of all the store
        List<String> zipOfAllStores = response.extract().path("data.zip");
        System.out.println("Zip of all stores : " +zipOfAllStores);

        //18. Find the zip of store name = Roseville
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("Zip of the store Roseville is :" + zip);

        //19. Find the storeservices details of the service name = Magnolia Home Theater
        List<String> storeServicesDetails = response.extract().path("data.find{it.services}.services.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("Store Services details : " + storeServicesDetails);

        //20. Find the lat of all the stores
        List<Double> latOfAllStores = response.extract().path("data.lat");
        System.out.println("Lat of all stores : " +latOfAllStores);
    }
}