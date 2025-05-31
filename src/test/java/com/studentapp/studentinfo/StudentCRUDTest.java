package com.studentapp.studentinfo;

import com.studentapp.constant.EndPoints;
import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by Jay Vaghani
 */
public class StudentCRUDTest extends TestBase {

    static String firstName = TestUtils.getRandomValue() + "PrimeUserFName";
    static String lastName = TestUtils.getRandomValue() + "PrimeUserLName";
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "prime@gmail.com";
    static int studentId;

    @Test
    public void test001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("C#");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .post();
        response.then().log().ifValidationFails().statusCode(201);

    }

    @Test
    public void test002() {
        String s1 = "findAll{it.firstName == '";
        String s2 = "'}.get(0)";

        ValidatableResponse response = given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .then().log().ifValidationFails().statusCode(200);

        HashMap<String, Object> studentMap = response.extract().path(s1 + firstName + s2);
        studentId = (int) studentMap.get("id");
        System.out.println(studentId);

    }

    @Test
    public void test003() {

        String firstName = StudentCRUDTest.firstName + "_updated";

        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("C#");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("studentId", studentId)
                .when()
                .body(studentPojo)
                .put(EndPoints.UPDATE_STUDENT_BY_ID);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {
        given().log().all()
                .pathParam("studentId", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then().log().all().statusCode(204);

        given().log().all()
                .pathParam("studentId", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then().log().all()
                .statusCode(404);
    }
}