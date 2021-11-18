package th.co.scb.restapi.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;
import th.co.scb.restapi.repository.ScbWeFixedAgentRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ScbWebFixedAgentService {

    @Autowired
    ScbWeFixedAgentRepository scbWeFixedAgentRepository;

    public ScbReportSaveResponse reportSave(String requestUID, ScbReportAgentSaveRequest req) {
        int ii = scbWeFixedAgentRepository.updateReportFixed(requestUID, req);
        if (ii > 0) {
            return new ScbReportSaveResponse("0000", "SUCCESS");
        } else {
            return new ScbReportSaveResponse("0001", "FAIL");
        }
    }

    public ScbReportSaveResponse reviewSave(String requestUID,ScbReportAgentSaveRequest req) {
        int ii = scbWeFixedAgentRepository.updateReviewAfterFix(requestUID, req);
        if (ii > 0) {
            return new ScbReportSaveResponse("0000", "SUCCESS");
        } else {
            return new ScbReportSaveResponse("0001", "FAIL");
        }
    }
}
