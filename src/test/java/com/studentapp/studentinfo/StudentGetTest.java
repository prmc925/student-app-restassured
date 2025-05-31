package com.studentapp.studentinfo;

import com.studentapp.constant.EndPoints;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentsInfo() {
        Response response = given()
                .when()
                .get(EndPoints.GET_ALL_STUDENTS);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleStudentInfo() {
        Response response = given()
                .pathParam("studentId", 88)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void searchStudentWithParameter() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("programme", "Computer Science");
        qParams.put("limit", 3);

        Response response = given()
                /*.queryParam("programme", "Computer Science")
                .queryParam("limit", 3)*/
                .queryParams(qParams)
                .when()
                .get(EndPoints.GET_ALL_STUDENTS);
        response.then().statusCode(200);
        response.prettyPrint();
    }


}