package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class StudentSerenitySteps {

	@Step("Creating a Student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
	public ValidatableResponse createStudent(String firstName, String lastName,
			String email, String programme, List<String> courses) {

		StudentClass stu = new StudentClass();
		stu.setFirstName(firstName);
		stu.setLastName(lastName);
		stu.setEmail(email);
		stu.setProgramme(programme);
		stu.setCourses(courses);

		return SerenityRest.given().spec(ReusableSpecifications.getGenricReuestSpec()).log().all()
				.body(stu).post().then();

	}
	
	@Step("Getting studentinfo with FisrtName:{0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName)
	{
		String p1 = "find{it.firstName=='";
		String p2 = "'}";


		return SerenityRest.given()
				.contentType(ContentType.JSON).log().all().get("/list").then()
				.extract().path(p1 + firstName + p2);
	}
	
	@Step("Updating a Student with id:{0},firstName:{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
	public ValidatableResponse updateStudent(int studentId,String firstName, String lastName,
			String email, String programme, List<String> courses) {

		StudentClass stu = new StudentClass();
		stu.setFirstName(firstName);
		stu.setLastName(lastName);
		stu.setEmail(email);
		stu.setProgramme(programme);
		stu.setCourses(courses);

		return SerenityRest.given().spec(ReusableSpecifications.getGenricReuestSpec()).log().all()
				.body(stu).put("/"+studentId).then();

	}
	
	
	@Step("Deleting Student info with ID:{0}")
	public void deleteStudent(int studentId)
	{
		SerenityRest.given().spec(ReusableSpecifications.getGenricReuestSpec()).log().all()
		.delete("/" + studentId);

	}
	
	@Step("Getting Student info with ID:{0}")
	public ValidatableResponse getStundetById(int studentId)
	{
		return SerenityRest.given().spec(ReusableSpecifications.getGenricReuestSpec()).log().all()
		.get("/" + studentId).then();
	}

}
