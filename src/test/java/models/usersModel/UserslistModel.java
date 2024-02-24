package models.usersModel;

import lombok.Data;

@Data
public class UserslistModel {
    int page;
    int per_page;
    int total;
    int total_pages;
    private UserDataResponseModel[] data;
    private UsersSupportResponseModel support;
}
