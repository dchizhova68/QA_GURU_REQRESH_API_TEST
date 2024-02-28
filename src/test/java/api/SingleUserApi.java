package api;

import models.usersModel.UsersResponseModel;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.singleUserRequestSpec;
import static specs.Specs.successSingleUserResponseSpec;

public class SingleUserApi {
    public static UsersResponseModel getUserInfo(String userId) {

        return given(singleUserRequestSpec)
                .basePath("/api/users/" + userId)
                .get()
                .then()
                .spec(successSingleUserResponseSpec)
                .extract().as(UsersResponseModel.class);
    }

    public static void checkEmailUser(String email, UsersResponseModel userInfo) {
        assertEquals(email, userInfo.getData().getEmail());
    }

}
