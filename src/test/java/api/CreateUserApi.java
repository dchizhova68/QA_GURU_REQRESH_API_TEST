package api;

import models.createUserModel.CreateUserRequestModel;
import models.createUserModel.CreateUserResponseModel;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.createUserRequestSpec;
import static specs.Specs.successfulCreateUserResponseSpec;

public class CreateUserApi {

    public static CreateUserResponseModel createUser(String name, String job) {
        CreateUserRequestModel userData = new CreateUserRequestModel();
        userData.setName(name);
        userData.setJob(job);

        return given(createUserRequestSpec)
                .body(userData)
                .when()
                .post()
                .then()
                .spec(successfulCreateUserResponseSpec)
                .extract().as(CreateUserResponseModel.class);
    }

    public static void checkUserDataFromResponse(String name, String job, CreateUserResponseModel userData) {
        assertEquals(name, userData.getName());
        assertEquals(job, userData.getJob());
    }

}
