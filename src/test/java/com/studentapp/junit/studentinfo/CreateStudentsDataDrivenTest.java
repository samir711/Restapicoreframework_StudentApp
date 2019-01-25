package com.studentapp.junit.studentinfo;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@UseTestDataFrom("src/test/testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Steps
   StudentSerenitySteps steps;

    @Title("Data Driven Test for Adding Multiple Students to StudentApp.")
    @Test
    public void createMutipleStudents() {

        ArrayList<String> courses = new ArrayList<String>();
        courses.add(course);

        steps.createStudent(firstName, lastName, email, programme, courses);



    }


}
