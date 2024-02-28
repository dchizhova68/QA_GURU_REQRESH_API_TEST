package api;

import models.usersModel.UserDataResponseModel;
import models.usersModel.UserslistModel;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.successUserListResponseSpec;
import static specs.Specs.userListRequestSpec;

public class UserListApi {
    public static UserslistModel getUserList() {
        return given(userListRequestSpec)
                .get()
                .then()
                .spec(successUserListResponseSpec)
                .extract().as(UserslistModel.class);
    }

    public static void checkUserEmail(String email, String firstName, String lastName, UserslistModel userList) {
        for (UserDataResponseModel user : userList.getData()) {
            if (user.getFirstName() == firstName && user.getLastName() == lastName) {
                assertEquals(email, user.getEmail());
            }
        }
    }
}
