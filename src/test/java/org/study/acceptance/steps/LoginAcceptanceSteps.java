package org.study.acceptance.steps;

import io.restassured.RestAssured;
import org.springframework.http.MediaType;
import org.study.auth.application.dto.LoginRequestDto;
import org.study.auth.application.dto.UserAccessTokenResponseDto;

public class LoginAcceptanceSteps {

    public static String requestLoginGetToken(LoginRequestDto dto) {
        UserAccessTokenResponseDto res = RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);
        return res.accessToken();
    }

    public static Integer requestLoginGetResponseCode(LoginRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("login")
                .then()
                .extract()
                .jsonPath()
                .getObject("code", Integer.class);
    }

}
