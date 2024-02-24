package api;

import models.createUserModel.CreateUserResponseModel;
import models.usersModel.UserslistModel;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.*;

public class UserListApi {
    public static UserslistModel getUserList() {
        return given(userListRequestSpec)
                .get()
                .then()
                .spec(successUserListResponseSpec)
                .extract().as(UserslistModel.class);
    }

    public static void checkUserEmail(String email, UserslistModel userList) {

        assertEquals(email, userList.getData()[2].getEmail());
    }
}
