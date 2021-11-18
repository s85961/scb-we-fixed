package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;

@Repository
public class ScbWeFixedAgentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateReportFixed(String requestUID, ScbReportAgentSaveRequest req) {
        int row = 0;
        try {
            Long newFixID = (Long) jdbcTemplate.queryForMap("select IFNULL(max(fixID),0)+1 as nextID from report_fix where categoryID = ? and reportID = ? ", new Object[]{req.getCategoryID(), req.getReportID()}).get("nextID");
            System.out.println("newFixID:" + newFixID);
            String sql = " INSERT INTO `report_fix` " +
                    "(`categoryID`, `reportID`, `fixID`, `fixDescription`, `fixStatus`, `userID`, `fixDate`, `updateDate`) " +
                    " VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW()); ";

            row = jdbcTemplate.update(sql, new Object[]{req.getCategoryID(), req.getReportID(), newFixID, req.getFixDescription(), req.getFixStatus(), req.getUserID()});
        } catch (Exception e) {
            System.out.println("Exception [requestUID:" + requestUID + "] " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    public int updateReviewAfterFix(String requestUID, ScbReportAgentSaveRequest req) {
        int row = 0;
        try {
            String sql = " UPDATE `report_fix` set reportAccept = ? , reportDate = NOW() where categoryID = ? And reportID = ? AND reportAccept is null";
            row = jdbcTemplate.update(sql, new Object[]{req.getReportAccept(), req.getCategoryID(), req.getReportID()});
        } catch (Exception e) {
            System.out.println("Exception [requestUID:" + requestUID + "] " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }
}

