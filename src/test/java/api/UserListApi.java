package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Specs.successUserListResponseSpec;
import static specs.Specs.userListRequestSpec;

public class UserListApi {

    public static Response getUserList() {
        //  return get("/api/users?page=1");
        return given(userListRequestSpec)
                .get()
                .then()
                .spec(successUserListResponseSpec)
                .extract()
                .response();
    }

    public static void checkUserEmail(String email, String firstName, String lastName, Response userList) {
        String userEmail = userList.path("data.findAll {it.first_name == '%s'}.find {it.last_name == '%s'}.email", firstName, lastName);
        assertEquals(userEmail, email);
    }
}
