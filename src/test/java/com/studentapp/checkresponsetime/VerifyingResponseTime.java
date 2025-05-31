package com.studentapp.checkresponsetime;

import com.studentapp.constant.EndPoints;
import com.studentapp.testbase.TestBase;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/**
 * Created by Jay
 */
public class VerifyingResponseTime extends TestBase {

    @Test
    public void test001() {

        long responseTime = given()
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .time();
        System.out.println("The response time " + responseTime);

        given()
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .then()
                .time(lessThan(100l), TimeUnit.MILLISECONDS);

    }

}