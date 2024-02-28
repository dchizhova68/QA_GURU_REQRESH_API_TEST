package tests;

import api.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.createUserModel.CreateUserResponseModel;
import models.errorResponseModel.ErrorResponseModel;
import models.registerModel.RegisterResponseModel;
import models.usersModel.UsersResponseModel;
import models.usersModel.UserslistModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("Проверка api запросов сайта https://reqres.in")
public class ReqresInTests extends TestBase {
    @Disabled
    @Owner("Darya Chizhova")
    @Feature("Тестирование данных пользователя")
    @Test
    @DisplayName("Проверка почты пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Tag("USER_DATA")
    void checkUserEmailByUserIdTest() {
        UsersResponseModel userInfo = step("Запрашиваем данные пользователя", () ->
                SingleUserApi.getUserInfo("3")
        );

        step("Проверяем почту пользователя", () ->
                SingleUserApi.checkEmailUser("emma.wong@reqres.in", userInfo)
        );
    }

    @Disabled
    @Owner("Darya Chizhova")
    @Test
    @Feature("Тестирование запроса на создание нового пользователя")
    @DisplayName("Проверка создания нового пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("ACCOUNT")
    void succsessfulCreateUserTest() {
        CreateUserResponseModel userData = step("Создаем нового пользовтеля", () ->
                CreateUserApi.createUser("Chemist", "Diana Rose")
        );
        step("Проверяем, что в ответе пришли введенные данные пользователя ", () ->
                CreateUserApi.checkUserDataFromResponse("Chemist", "Diana Rose", userData)
        );
    }

    @Disabled
    @Owner("Darya Chizhova")
    @Test
    @Feature("Тестирование запроса на регистрацию")
    @DisplayName("Проверка успешной регистрации")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("ACCOUNT")
    void succsessfulRegisterTest() {
        RegisterResponseModel userInfo = step("Отправляем запрос на регистрацию", () ->
                RegisterApi.registerUser("eve.holt@reqres.in", "pistol")
        );

        step("Проверяем токен", () ->
                RegisterApi.checkToken("QpwL5tke4Pnpja7X4", userInfo)
        );
    }

    @Disabled
    @Owner("Darya Chizhova")
    @Test
    @Feature("Тестирование запроса на регистрацию")
    @DisplayName("Проверка ошибки при регистрации без пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ACCOUNT")
    void unsuccsessfuldRegisterTest() {
        ErrorResponseModel errorResponse = step("Отправляем запрос на регистрацию", () ->
                RegisterApi.registerUserWithoutPassword("eve.holt@reqres.in")
        );

        step("Проверяем текст ошибки в ответе", () ->
                RegisterApi.checkErrorRegister(errorResponse)
        );
    }

    @Disabled
    @Owner("Darya Chizhova")
    @Test
    @Feature("Тестирование запроса на удаление пользователя")
    @DisplayName("Проверка удаления пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ACCOUNT")
    void deleteUserTest() {
        CreateUserResponseModel userData = step("Создаем нового пользовтеля", () ->
                CreateUserApi.createUser("Chemist", "Test User")
        );
        step("Удаляем пользователя", () ->
                DeleteApi.deleteUser(userData.getId())
        );
    }


    @Owner("Darya Chizhova")
    @Test
    @Feature("Тестирование данных пользователя")
    @DisplayName("Проверка соответствия почты пользователю")
    @Severity(SeverityLevel.NORMAL)
    @Tag("USER_DATA")
    void findUserInlistTest() {

        UserslistModel userList = step("Отправляем запрос на получение списка пользователей", () ->
                UserListApi.getUserList()
        );

        step("Проверяем почту пользователя из списка", () ->
                UserListApi.checkUserEmail("emma.wong@reqres.in", "Emma", "Wong", userList)
        );

    }
}
