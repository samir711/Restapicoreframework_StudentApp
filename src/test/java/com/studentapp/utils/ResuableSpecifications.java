package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ResuableSpecifications {

    public static RequestSpecBuilder repec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder respec;
    public static ResponseSpecBuilder responseSpecBuilder;

    public static RequestSpecification getGenericRequestSpec() {

        repec = new RequestSpecBuilder();
        repec.setContentType(ContentType.JSON);
        requestSpecification = repec.build();
        return requestSpecification;

    }








}
