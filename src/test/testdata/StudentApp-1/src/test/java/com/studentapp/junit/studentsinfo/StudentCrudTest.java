package com.studentapp.junit.studentsinfo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.HashMap;

import net.serenitybdd.core.Serenity.SerenityConfigurer;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import org.hamcrest.Matchers;
import org.jruby.ir.operands.Array;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.BaseTest;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtlis;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentCrudTest extends BaseTest {

	@Steps
	StudentSerenitySteps steps;

	static String randomValue = TestUtlis.getRandomValue(); // 7632890

	String firstName = "TestStudent_" + randomValue; // TestStudent_7632890

	String lastName = "TestStudent_" + randomValue;

	String programme = ("CSE");

	String email = randomValue + "xyz@text.com";

	static int studentId;

	@Title("This test will create a new Student")
	@Test
	public void test001() {

		ArrayList<String> courses = new ArrayList<String>();
		courses.add("Java");

		steps.createStudent(firstName, lastName, email, programme, courses)
				.log().all().spec(ReusableSpecifications.getGenricResponseSpec());

	}

	@Title("This test will verify newly added student")
	@Test
	public void test002() {

		HashMap<String, Object> value = steps
				.getStudentInfoByFirstName(firstName);

		assertThat(value, Matchers.hasValue(lastName));

		studentId = (int) value.get("id");

		System.out.println(studentId);

	}

	@Title("This test will update student information and Verify the info got updated")
	@Test
	public void test003() {

		firstName = firstName + "_Updated";

		ArrayList<String> courses = new ArrayList<String>();
		courses.add("Java");

		steps.updateStudent(studentId, firstName, lastName, email, programme,
				courses);

		HashMap<String, Object> value = steps
				.getStudentInfoByFirstName(firstName);

		assertThat(value, Matchers.hasValue(firstName));

	}

	@Title("This test will delete the student and will also verify")
	@Test
	public void test004() {
		steps.deleteStudent(studentId);

		steps.getStundetById(studentId).statusCode(404);

	}

}
