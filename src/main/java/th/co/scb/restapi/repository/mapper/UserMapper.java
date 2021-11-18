package th.co.scb.restapi.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import th.co.scb.restapi.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setPhoneNo(resultSet.getString("phoneNo"));
        user.setSocialId(resultSet.getString("socialID"));
        user.setUserId(resultSet.getString("userID"));
        user.setUserGroupId(resultSet.getString("userGroupID"));
        return user;
    }
}