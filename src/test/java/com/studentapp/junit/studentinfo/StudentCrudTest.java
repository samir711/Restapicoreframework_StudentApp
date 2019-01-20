package com.studentapp.junit.studentinfo;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.BaseTest;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matcher;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

/*

https://stackoverflow.com/questions/20197783/how-to-use-hamcrest-to-inspect-map-items
http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html#hasItem(org.hamcrest.Matcher)

 */

public class StudentCrudTest extends BaseTest {

    static String randomValue = TestUtils.getRandomValue();
    String firstName = "TestStudent_" + randomValue;
    String lastName = "TestStudent_" + randomValue;
    String email = randomValue +"xyz@text.com";

    @Test
    public void createStudent() {

        StudentClass stu = new StudentClass();
        stu.setFirstName(firstName);
        stu.setLastName(lastName);
        stu.setEmail(email);
        stu.setProgramme("CSE");

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        stu.setCourses(courses);
        SerenityRest.given().contentType(ContentType.JSON).log().all().body(stu).post().then()
                .log().all();
    }

    @Test
    public void verifyIfStudentAdded(){


    String p1 = "findAll{it.firstName=='";
    String p2 = "'}.get(0)";
        System.out.println(p1+firstName+p2);

   HashMap<String,Object> value=   SerenityRest.given().contentType(ContentType.JSON).log().all().get("/list").then()
                .extract().path(p1+firstName + p2);
        System.out.println(value);

       //assertThat(value.values(), hasItem(lastName));
       assertThat(value.values(), hasItem(lastName));

    }

}
