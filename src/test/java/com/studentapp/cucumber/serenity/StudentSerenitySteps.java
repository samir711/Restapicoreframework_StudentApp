package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating a Student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses)
    {
        StudentClass stu = new StudentClass();
        stu.setFirstName(firstName);
        stu.setLastName(lastName);
        stu.setEmail(email);
        stu.setProgramme(programme);
        stu.setCourses(courses);
        return SerenityRest.given().contentType(ContentType.JSON).log().all().body(stu).post().then();
    }
}
