package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.scb.restapi.model.ReportMax;
import th.co.scb.restapi.model.ReportSaveRequest;
import th.co.scb.restapi.model.User;
import th.co.scb.restapi.repository.mapper.ReportMaxMapper;
import th.co.scb.restapi.repository.mapper.UserMapper;

import java.util.Base64;
import java.util.List;

@Repository
public class ScbWeFixedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(ReportSaveRequest reportSaveRequest) {
        StringBuffer sqlMax = new StringBuffer("SELECT MAX(reportID)+1 as COUNT from report_info");
        List<ReportMax> reportMaxes = jdbcTemplate.query(sqlMax.toString(), new Object[]{}, new ReportMaxMapper());
        int count = reportMaxes.get(0).getCount();
        System.out.println("count " + count);

        byte[] picture1 = null;
        byte[] picture2 = null;
        byte[] picture3 = null;

        if (reportSaveRequest.getPicture1() != null) {
            picture1 = covertBase64ToByte(reportSaveRequest.getPicture1());
        }

        if (reportSaveRequest.getPicture2() != null) {
            picture2 = covertBase64ToByte(reportSaveRequest.getPicture2());
        }

        if (reportSaveRequest.getPicture3() != null) {
            picture3 = covertBase64ToByte(reportSaveRequest.getPicture3());
        }

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        sql.append(" report_info (categoryID, reportID, topic, description, pic1, pic2, pic3, latitude, longitude, userID) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        return jdbcTemplate.update(sql.toString(),
                reportSaveRequest.getCategoryId(), count, reportSaveRequest.getTopic(), reportSaveRequest.getDescription(),
                picture1, picture2, picture3,
                reportSaveRequest.getLatitude(), reportSaveRequest.getLongitude(), reportSaveRequest.getUserId());
    }

    private byte[] covertBase64ToByte(String base64) {
        return Base64.getDecoder().decode(base64.getBytes());
    }

    public User findUserBySocialId(String socialId) {
        StringBuffer sql = new StringBuffer(" select * from user_info where socialID LIKE ? ");
        try {
            List<User> reportMaxes = jdbcTemplate.query(sql.toString(), new Object[]{socialId}, new UserMapper());
            if (reportMaxes.size() == 0) {
                return null;
            } else {
                return reportMaxes.get(0);
            }
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public User findUserByAgent(String email, String password) {
        StringBuffer sql = new StringBuffer(" select * from user_info where email LIKE ? AND password LIKE ? ");
        try {
            List<User> reportMaxes = jdbcTemplate.query(sql.toString(), new Object[]{email, password}, new UserMapper());
            return reportMaxes.get(0);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public User user(User user) {
        StringBuffer sqlMax = new StringBuffer("SELECT MAX(userID)+1 as COUNT from user_info");
        List<ReportMax> reportMaxes = jdbcTemplate.query(sqlMax.toString(), new Object[]{}, new ReportMaxMapper());
        int userId = reportMaxes.get(0).getCount();
        System.out.println("userId " + userId);

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        sql.append(" user_info (userID, userGroupID, firstName, lastName, userStatus, email, password, phoneNo, socialID) ");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        int add = jdbcTemplate.update(sql.toString(),
                userId, "2", user.getFirstName(), user.getLastName(), 1, user.getEmail(), "", user.getPhoneNo(), user.getSocialId());
        if (add == 0) {
            return null;
        } else {
            user.setUserId(Integer.toString(userId));
            return user;
        }
    }
}

