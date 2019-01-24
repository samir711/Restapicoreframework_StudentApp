package com.studentapp.junit.studentinfo;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.BaseTest;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

/*

https://stackoverflow.com/questions/20197783/how-to-use-hamcrest-to-inspect-map-items
http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html#hasItem(org.hamcrest.Matcher)

 */


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentCrudTest extends BaseTest {

    @Steps
    StudentSerenitySteps steps;

    static String randomValue = TestUtils.getRandomValue();
    String firstName = "TestStudent_" + randomValue;
    String lastName = "TestStudent_" + randomValue;
    String email = randomValue +"xyz@text.com";
    static Object student_Id;
    String programme = "CSE";

    @Title("This test will create a new student")
    @Test
    public void test001() {

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        steps.createStudent(firstName, lastName, email,programme,courses).log().all();
    }

    @Title("This test will verify newly added student")
    @Test
    public void test002(){


    String p1 = "findAll{it.firstName=='";
    String p2 = "'}.get(0)";
        System.out.println(p1+firstName+p2);

   HashMap<String,Object> value=   SerenityRest.given().contentType(ContentType.JSON).log().all().get("/list").then()
                .extract().path(p1+firstName + p2);
        System.out.println(value);

       //assertThat(value.values(), hasItem(lastName));
       assertThat(value.values(), hasItem(lastName));
       student_Id = value.get("id");
        System.out.println("student id is " + student_Id);

    }

    @Title("This test will update student information and Verify the info got updated")
    @Test
    public void test003() {

        firstName = firstName + "Updated";

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        int studentId;
        studentId = (((Integer) student_Id).intValue());
        steps.updateStudent(studentId,firstName,lastName,email,programme,courses);
        HashMap<String,Object> value=  steps.GetStudentInfoByFirstName(firstName);
        System.out.println(value);
        assertThat(value.values(), hasItem(firstName));
    }

    @Title("This test will delete the student and will also verify")
    @Test
    public void test004() {

       steps.deleteStudent((((Integer) student_Id).intValue()));
       steps.getStudentById((((Integer) student_Id).intValue())).statusCode(404);
        //System.out.println(result);

    }



}
