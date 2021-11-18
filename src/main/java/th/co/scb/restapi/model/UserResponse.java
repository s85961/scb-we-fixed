package th.co.scb.restapi.model;

import lombok.Value;

@Value
public class UserResponse {
    private String code;
    private String message;
    private User user;
}
