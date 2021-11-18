package th.co.scb.restapi.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import th.co.scb.restapi.model.ReportMax;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMaxMapper implements RowMapper<ReportMax> {

    @Override
    public ReportMax mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        ReportMax reportMax = new ReportMax();
        reportMax.setCount(resultSet.getInt("COUNT"));
        return reportMax;
    }
}