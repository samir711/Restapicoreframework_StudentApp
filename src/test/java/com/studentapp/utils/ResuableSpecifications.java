package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class ResuableSpecifications {

    public static RequestSpecBuilder repec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder respec;
    public static ResponseSpecification responseSpecification;

    public static RequestSpecification getGenericRequestSpec() {

        repec = new RequestSpecBuilder();
        repec.setContentType(ContentType.JSON);
        requestSpecification = repec.build();
        return requestSpecification;

    }

     public static ResponseSpecification getGenericResponseSpec() {

        respec = new ResponseSpecBuilder();
        respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
        respec.expectHeader("Transfer-Encoding","chunked");
        respec.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);
         responseSpecification = respec.build();
         return responseSpecification;


     }







}
