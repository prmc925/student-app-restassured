package com.studentapp.studentinfo;

import com.studentapp.constant.EndPoints;
import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentPutTest extends TestBase {

    @Test
    public void updateStudent() {

        List<String> courseList = new ArrayList<>();
        courseList.add("Jira");
        courseList.add("confluence");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("prime");
        studentPojo.setLastName("testing");
        studentPojo.setEmail("prime123@gmial.com");
        studentPojo.setProgramme("Manual Tester");
        studentPojo.setCourses(courseList);

        Response response = given()
                .pathParam("studentId", 101)
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .put(EndPoints.UPDATE_STUDENT_BY_ID);
        response.then().statusCode(200);
        response.prettyPrint();
    }
}