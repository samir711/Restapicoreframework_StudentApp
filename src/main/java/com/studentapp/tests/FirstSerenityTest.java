package com.studentapp.tests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

    @BeforeClass
    public static void init() {

        RestAssured.baseURI = "http://localhost:8080/student";

    }

    @Test
    public void getAllStudent() {

       // RestAssured.given().contentType(ContentType.JSON).log().all().get("/list").then().statusCode(200);
        SerenityRest.given().contentType(ContentType.JSON).log().all().get("/list").then().statusCode(200);

    }

    @Test
    public void thisIsFailingTest() {

        // RestAssured.given().contentType(ContentType.JSON).log().all().get("/list").then().statusCode(200);
        SerenityRest.given().contentType(ContentType.JSON).log().all().get("/list").then().statusCode(201);

    }

    @Pending
    @Test
    public void thisIsPendingTest() {

    }

    @Test
    public void ErrorTest() {
        System.out.println(5/0);
    }

    @Manual
    @Test
    public void ManualTest() {

    }

}
