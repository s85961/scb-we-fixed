package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.model.ScbReportAgentSearchRequest;

import java.util.List;

@Repository
public class ScbWeFixedSearchRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List search(String requestUID, ScbReportAgentSearchRequest req) {
        List list = null;
        try {
            String sql = "SELECT rri.*,fix.*,IFNULL(vote.voteUp,0) AS voteUp FROM ( " +
                    " " +
                    "SELECT ri.*  " +
                    ", (  " +
                    "111.111 * " +
                    "DEGREES(ACOS(LEAST(1.0, COS(RADIANS(ri.latitude)) " +
                    "* COS(RADIANS(param.lat)) " +
                    "* COS(RADIANS(ri.longitude - param.lng)) " +
                    "+ SIN(RADIANS(ri.latitude)) " +
                    "* SIN(RADIANS(param.lat)))))) distanceKM " +
                    " FROM (SELECT ? AS lat, ? AS lng) param, " +
                    " report_info ri " +
                    " where categoryID = ? " +
                    " having distanceKM <= ? " +
                    " ) rri " +
                    "  LEFT JOIN  " +
                    " (SELECT categoryID,reportID,GROUP_CONCAT(fixID ORDER BY fixID) AS fixIDs,GROUP_CONCAT(fixDescription ORDER BY fixID) AS fixDescriptions from report_fix GROUP BY categoryID,reportID) fix ON fix.categoryID = rri.categoryID and fix.reportID = rri.reportID  " +
                    " LEFT JOIN  " +
                    "(SELECT categoryID,reportID,COUNT(1) AS VoteUp FROM report_vote WHERE voteUp = 1 GROUP BY categoryID,reportID) vote ON vote.categoryID = rri.categoryID and vote.reportID = rri.reportID" +
                    " ORDER BY rri.createDate desc ";

            list = jdbcTemplate.queryForList(sql, new Object[]{req.getLatitude(), req.getLongitude(), req.getCategoryID(), req.getInAreaKM()});
        } catch (Exception e) {
            System.out.println("Exception [requestUID:" + requestUID + "] " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

}

