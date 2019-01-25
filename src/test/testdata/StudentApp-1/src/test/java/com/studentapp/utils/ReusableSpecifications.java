package com.studentapp.utils;

import java.util.concurrent.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThan;

public class ReusableSpecifications {

	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestspecification;

	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responsespecification;

	public static RequestSpecification getGenricReuestSpec() {

		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestspecification = rspec.build();
		return requestspecification;

	}

	public static ResponseSpecification getGenricResponseSpec() {

		respec = new ResponseSpecBuilder();
		respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respec.expectHeader("Transfer-Encoding", "chunkedyuyjff");
		respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responsespecification = respec.build();
		return responsespecification;

	}

}
