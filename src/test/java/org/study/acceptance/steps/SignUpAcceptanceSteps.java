package org.study.acceptance.steps;

import io.restassured.RestAssured;
import org.springframework.http.MediaType;
import org.study.auth.application.dto.SendEmailRequestDto;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/send-verification-email")
                .then().log().all()
                .extract()
                .jsonPath().get("code");
    }

}
