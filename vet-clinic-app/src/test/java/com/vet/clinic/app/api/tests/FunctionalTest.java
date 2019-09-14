package com.vet.clinic.app.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.clinic.app.web.rest.appointment.AppointmentDto;
import com.vet.clinic.app.web.rest.user.AuthenticationRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Slf4j
public class FunctionalTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    private String token;

    @Before
    public void setup() {
        RestAssured.port = this.port;
        token = given()
            .contentType(ContentType.JSON)
            .body(AuthenticationRequest.builder().username("user").password("password").build())
            .when().post("/vsp/login")
            .andReturn().jsonPath().getString("token");
        
        
        //admin use case test
//        token = given()
//            .contentType(ContentType.JSON)
//            .body(AuthenticationRequest.builder().username("admin").password("password").build())
//            .when().post("/vsp/login")
//            .andReturn().jsonPath().getString("token");
    }

    @Test
    public void getAllVehicles() throws Exception {
        
         given()
         .header("Authorization", "Bearer "+token)
            .accept(ContentType.JSON)

        .when()
            .get("/vsp/appointments")

        .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
         
    }

    @Test
    public void testAdd_app() throws Exception {
        AppointmentDto dto = new AppointmentDto();
        given()

            .contentType(ContentType.JSON)
            .body(dto)

        .when()
            .post("/vsp/appointments")

        .then()
            .statusCode(401);

        
    }

    @Test
    //@Ignore
    public void test_add_token() throws Exception {
      AppointmentDto dto = new AppointmentDto();
      
        given()
            .header("Authorization", "Bearer "+token)
            .contentType(ContentType.JSON)
            .body(dto)

        .when()
            .post("/vsp/appointments")

        .then()
            .statusCode(403);

        
    }

}
