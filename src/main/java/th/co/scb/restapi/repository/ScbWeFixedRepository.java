package th.co.scb.restapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScbWeFixedRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(String ScbReportSaveRequest) {
        return 0;
    }
}

