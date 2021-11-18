package th.co.scb.restapi.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String socialId;
    private String userGroupId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNo;
}
