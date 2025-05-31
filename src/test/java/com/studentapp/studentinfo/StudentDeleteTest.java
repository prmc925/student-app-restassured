package com.studentapp.studentinfo;

import com.studentapp.constant.EndPoints;
import com.studentapp.testbase.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/*
 *  Created by Jay
 */
public class StudentDeleteTest extends TestBase {

    @Test
    public void deleteTheStudent(){
        Response response = given().log().all()
                .pathParam("studentId", 1)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID);
        response.then().statusCode(204);
    }

}
