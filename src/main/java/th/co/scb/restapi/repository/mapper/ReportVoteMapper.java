package th.co.scb.restapi.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import th.co.scb.restapi.model.ReportVote;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportVoteMapper implements RowMapper<ReportVote> {

    @Override
    public ReportVote mapRow(ResultSet resultSet, int i) throws SQLException {
        ReportVote reportVote = new ReportVote();
        reportVote.setVoteAmount(resultSet.getInt("COUNT"));
        return reportVote;
    }
}