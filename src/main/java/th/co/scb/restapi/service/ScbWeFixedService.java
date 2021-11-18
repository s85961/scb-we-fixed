package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;
import th.co.scb.restapi.repository.ScbWeFixedRepository;

@Service
public class ScbWeFixedService {

    @Autowired
    private ScbWeFixedRepository weFixedRepository;

    public ScbReportSaveResponse reportSave(ScbReportSaveRequest reportSaveRequest) {

        int save = weFixedRepository.save(reportSaveRequest);

        if (save == 0) {
            return new ScbReportSaveResponse("0001", "ERROR");
        }

        return new ScbReportSaveResponse("0000", "SUCCESS");
    }
}
