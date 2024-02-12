package com.kyattonippu.api.tests;

import com.kyattonippu.api.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.kyattonippu.api.data.ApiEndpoints.*;
import static com.kyattonippu.api.data.TestsData.*;
import static com.kyattonippu.api.specs.ReqresSpecs.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

@DisplayName("API tests for reqres.in")
public class ApiTests extends TestBase {

    @DisplayName("Verification of single user data")
    @Test
    void getSingleUserTest() {

        SingleUserResponse response = step("Send request to see single user data", () ->
                given(requestGetDeleteSpec)
                        .get(USERS + "/2")
                        .then()
                        .spec(response200)
                        .extract().as(SingleUserResponse.class));

        step("Check that data in the answer matches with the expected", () -> {
            assertThat(response.getData().getId()).isEqualTo(SINGLE_USER_ID);
            assertThat(response.getData().getEmail()).isEqualTo(SINGLE_USER_EMAIL);
            assertThat(response.getData().getFirstName()).isEqualTo(FIRST_NAME);
            assertThat(response.getData().getLastName()).isEqualTo(LAST_NAME);
            assertThat(response.getData().getAvatar()).isEqualTo(AVATAR);
            assertThat(response.getSupport().getUrl()).isEqualTo(URL);
            assertThat(response.getSupport().getText()).isEqualTo(TEXT);
        });
    }

    @Test
    @DisplayName("Create user")
    void createUser() {
        CreateAndUpdateUserRequest userData = new CreateAndUpdateUserRequest();
        userData.setName(NAME);
        userData.setJob(JOB_TITLE);

        CreateAndUpdateUserResponse response = step("Send request to create user", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(USERS)
                        .then()
                        .spec(response201)
                        .extract().as(CreateAndUpdateUserResponse.class));

        step("Check that user was created", () -> {
            assertThat(response.getName()).isEqualTo(NAME);
            assertThat(response.getJob()).isEqualTo(JOB_TITLE);
        });
    }

    @DisplayName("Update User")
    @Test
    void updateUser() {
        CreateAndUpdateUserRequest userData = new CreateAndUpdateUserRequest();
        userData.setName(NAME);
        userData.setJob(NEW_JOB_TITLE);

        CreateAndUpdateUserResponse response = step("Send request to update user data", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(USERS)
                        .then()
                        .spec(response201)
                        .extract().as(CreateAndUpdateUserResponse.class));

        step("Check that user data was updated", () -> {
            assertThat(response.getName()).isEqualTo(NAME);
            assertThat(response.getJob()).isEqualTo(NEW_JOB_TITLE);
        });
    }

    @DisplayName("Register Successful")
    @Test
    void registerSuccessful() {
        RegisterUserRequest userData = new RegisterUserRequest();
        userData.setEmail(EMAIL);
        userData.setPassword(PASSWORD);

        RegisterUserResponse response = step("Send request to register user", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(REGISTER)
                        .then()
                        .spec(response200)
                        .extract().as(RegisterUserResponse.class));

        step("Check that register was successful", () -> {
            assertThat(response.getId()).isEqualTo(ID);
            assertThat(response.getToken()).isEqualTo(TOKEN);
        });
    }

    @DisplayName("Register Unsuccessful")
    @Test
    void registerUnsuccessful() {
        RegisterUserRequest userData = new RegisterUserRequest();
        userData.setEmail(EMAIL);

        RegisterUserResponse response = step("Send request to register user without password", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(REGISTER)
                        .then()
                        .spec(response400)
                        .extract().as(RegisterUserResponse.class));

        step("Check that error message appeared", () -> {
            assertThat(response.getError()).isEqualTo(ERROR_MESSAGE);
        });
    }

    @DisplayName("Login Successful")
    @Test
    void loginSuccessful() {
        LoginUserRequest userData = new LoginUserRequest();
        userData.setEmail(EMAIL);
        userData.setPassword(PASSWORD2);

        LoginUserResponse response = step("Send request to login user", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(LOGIN)
                        .then()
                        .spec(response200)
                        .extract().as(LoginUserResponse.class));

        step("Check that login was successful", () -> {
            assertThat(response.getToken()).isEqualTo(TOKEN);
        });
    }

    @DisplayName("Login Unsuccessful")
    @Test
    void loginUnsuccessful() {
        LoginUserRequest userData = new LoginUserRequest();
        userData.setEmail(EMAIL2);

        LoginUserResponse response = step("Send request to login user without password", () ->
                given(requestPostPutSpec)
                        .body(userData)
                        .when()
                        .post(LOGIN)
                        .then()
                        .spec(response400)
                        .extract().as(LoginUserResponse.class));

        step("Check that error message appeared", () -> {
            assertThat(response.getError()).isEqualTo(ERROR_MESSAGE);
        });
    }

    @DisplayName("Delete User")
    @Test
    void deleteUser() {

        step("Send request to delete user " +
                "expected status code recieved", () -> {
            given(requestGetDeleteSpec)
                    .delete(USERS + "/2")
                    .then()
                    .spec(response204);
        });
    }

    @DisplayName("User not found")
    @Test
    void getUserNotFound() {

        step("Send request to find user " +
                "expected status code recieved", () -> {
            given(requestNotFoundSpec)
                    .get(USERS + "/23")
                    .then()
                    .spec(response404);
        });
    }

}


