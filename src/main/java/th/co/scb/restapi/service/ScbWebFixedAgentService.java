package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ReportSaveResponse;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.repository.ScbWeFixedAgentRepository;

@Service
public class ScbWebFixedAgentService {

    @Autowired
    ScbWeFixedAgentRepository scbWeFixedAgentRepository;

    public ReportSaveResponse reportSave(String requestUID, ScbReportAgentSaveRequest req) {
        int ii = scbWeFixedAgentRepository.updateReportFixed(requestUID, req);
        if (ii > 0) {
            return new ReportSaveResponse("0000", "SUCCESS");
        } else {
            return new ReportSaveResponse("0001", "FAIL");
        }
    }

    public ReportSaveResponse reviewSave(String requestUID, ScbReportAgentSaveRequest req) {
        int ii = scbWeFixedAgentRepository.updateReviewAfterFix(requestUID, req);
        if (ii > 0) {
            return new ReportSaveResponse("0000", "SUCCESS");
        } else {
            return new ReportSaveResponse("0001", "FAIL");
        }
    }
}
