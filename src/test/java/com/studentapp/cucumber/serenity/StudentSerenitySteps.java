package com.studentapp.cucumber.serenity;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ResuableSpecifications;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;

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
        return SerenityRest.given().spec(ResuableSpecifications.getGenericRequestSpec()).log().all().body(stu).post().then();
    }

    @Step("Creating a Student with id:{0} firstName:{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
    public ValidatableResponse updateStudent(int studentId, String firstName,String lastName,String email, String programme, List<String> courses) {

        StudentClass stu = new StudentClass();
        stu.setFirstName(firstName);
        stu.setLastName(lastName);
        stu.setEmail(email);
        stu.setProgramme(programme);
        stu.setCourses(courses);
        return SerenityRest.given().spec(ResuableSpecifications.getGenericRequestSpec()).log().all().body(stu).put("/" + studentId).then();
    }
    @Step("Getting student info with First Name: {0}")
    public HashMap<String,Object> GetStudentInfoByFirstName(String firstName) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        System.out.println(p1+firstName+p2);
        return given().spec(ResuableSpecifications.getGenericRequestSpec()).log().all().get("/list").then()
                .extract().path(p1+firstName + p2);
    }


    @Step("Deleting Student info with ID : {0}")
    public void deleteStudent(int studentId) {
        SerenityRest.given().spec(ResuableSpecifications.getGenericRequestSpec()).log().all().delete("/"+studentId);

    }

    @Step("Getting Student info with ID : {0}")
    public ValidatableResponse getStudentById(int studentID) {
       return SerenityRest.given().spec(ResuableSpecifications.getGenericRequestSpec()).log().all().get("/"+studentID).then();


    }
}
