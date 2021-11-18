package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.scb.restapi.model.ScbReportAddVoteRequest;
import th.co.scb.restapi.model.ScbReportMax;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.repository.mapper.StelReportMaxMapper;

import java.util.Base64;
import java.util.List;

@Repository
public class ScbWeFixedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(ScbReportSaveRequest reportSaveRequest) {
        StringBuffer sqlMax = new StringBuffer("SELECT MAX(reportID)+1 as COUNT from U_REPORT_WEFIX.report_info");
        List<ScbReportMax> reportMaxes = jdbcTemplate.query(sqlMax.toString(), new Object[]{}, new StelReportMaxMapper());
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

    public boolean insertAddVote(ScbReportAddVoteRequest addVoteRequest) {
        try {
            StringBuffer sql = new StringBuffer(" INSERT INTO report_vote (categoryID,reportID, voteUserID, voteUp, voteDown, createDate) ");
            sql.append(" VALUES (?, ?, ?, 1, 0 , now())");
            int rowEffect = jdbcTemplate.update(sql.toString(), addVoteRequest.getCategoryID(),addVoteRequest.getReportId(),addVoteRequest.getVoteUserId());
            return 1 == rowEffect;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}

