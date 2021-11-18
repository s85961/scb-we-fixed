package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.scb.restapi.model.ScbReportAddVoteRequest;

@Repository
public class ScbWeFixedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(String ScbReportSaveRequest) {
        return 0;
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

