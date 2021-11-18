package th.co.scb.restapi.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import th.co.scb.restapi.model.ScbReportMax;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StelReportMaxMapper implements RowMapper<ScbReportMax> {

    @Override
    public ScbReportMax mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        ScbReportMax reportMax = new ScbReportMax();
        reportMax.setCount(resultSet.getInt("COUNT"));
        return reportMax;
    }
}