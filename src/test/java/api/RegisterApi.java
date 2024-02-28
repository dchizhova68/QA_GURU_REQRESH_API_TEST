package api;

import models.errorResponseModel.ErrorResponseModel;
import models.registerModel.RegisterRequestModel;
import models.registerModel.RegisterResponseModel;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;

public class RegisterApi {
    public static RegisterResponseModel registerUser(String email, String password) {
        RegisterRequestModel userData = new RegisterRequestModel();
        userData.setEmail(email);
        userData.setPassword(password);

        return given(registerRequestSpec)
                .body(userData)
                .when()
                .post()
                .then()
                .spec(registerResponseSpec)
                .extract().as(RegisterResponseModel.class);
    }

    public static ErrorResponseModel registerUserWithoutPassword(String email) {
        RegisterRequestModel userData = new RegisterRequestModel();
        userData.setEmail(email);

        return given(registerRequestSpec)
                .body(userData)
                .when()
                .post()
                .then()
                .spec(erorr400ResponseSpec)
                .extract().as(ErrorResponseModel.class);
    }

    public static void checkToken(String token, RegisterResponseModel response) {
        assertEquals(token, response.getToken());
    }

    public static void checkErrorRegister(ErrorResponseModel response) {
        assertEquals("Missing password", response.getError());
    }

}
