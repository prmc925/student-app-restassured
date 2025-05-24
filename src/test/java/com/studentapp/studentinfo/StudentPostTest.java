package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {

        String email = TestUtils.getRandomValue() + "prime@gmail.com";

        List<String> courseList = new ArrayList<>();
        courseList.add("Automation");
        courseList.add("Java");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Jay");
        studentPojo.setLastName("Vaghani");
        studentPojo.setEmail(email);
        studentPojo.setProgramme("Automation Tester");
        studentPojo.setCourses(courseList);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);
    }
}