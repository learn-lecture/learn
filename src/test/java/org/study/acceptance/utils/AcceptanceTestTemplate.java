package org.study.acceptance.utils;

import static org.study.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.study.auth.application.dto.LoginRequestDto;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void init() {
        cleanUp.execute();
        loader.loadData();
    }

    protected String getEmailToken(String email) {
        return loader.getEmailToken(email);
    }

    protected void cleanUp() {
        cleanUp.execute();
    }

    protected boolean isEmailVerified(String email) {
        return loader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return loader.getUserId(email);
    }

    protected void createUser(String email) {
        loader.createUser(email);
    }

    protected String login(String email) {
        return requestLoginGetToken(new LoginRequestDto(email, "password"));
    }

}
