package com.bestbuy.crudtest;

import com.bestbuy.model.StoresPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    private String storeName = "Best Mobile";
    private String storeType = "BigBox";
    private String storeAddress = "B67 TVS Nagar";
    private String storeAddress2 = "Kajamalai";
    private String city = "Inver Grove Heights";
    private String state = "NP";
    private long zipcode = 55131;
    private double lat = 46.01561;
    private int storeId = 21;
    private int serviceId = 25;
    private double lng = -92.567438;
    private String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9";

    private long id = 4;
    private String storeServices;

    @Test
    public void createPost() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setStoreName(storeName);
        storesPojo.setStoreType(storeType);
        storesPojo.setStoreAddress(storeAddress);
        storesPojo.setStoreAddress2(storeAddress2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZipcode(zipcode);
        storesPojo.setLat(lat);
        storesPojo.setStoreId(storeId);
        storesPojo.setServiceId(serviceId);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setId(id);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .body(storesPojo)
                        .post();
        response.prettyPrint();
        //  id=response.then().extract().path("id");
        // response.then().log().all();
        response.then().statusCode(400);
    }

    @Test
    public void readGetSingleId() {
        Response response =
                given()
                        .when()
                        .get("/" + id);
        response.then().statusCode(200);
    }

    @Test
    public void getStores()
    {
        Response response =
                given()
                        .when()
                        .get();
        response.then().statusCode(200);
    }

    @Test
    public void patchUpdateId() {
        Response response =
                given()
                        .when()
                        .patch("/" + id);
        response.then().statusCode(200);
    }

    @Test
    public void deleteId() {
        given()
                .when()
                .delete("/" + 15)
                .then()
                .statusCode(404);

        given()
                .when()
                .get("/" + id)
                .then()
                .statusCode(404);
    }
}
