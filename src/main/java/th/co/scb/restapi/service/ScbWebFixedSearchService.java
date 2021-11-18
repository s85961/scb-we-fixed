package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.model.ScbReportAgentSearchRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;
import th.co.scb.restapi.repository.ScbWeFixedAgentRepository;
import th.co.scb.restapi.repository.ScbWeFixedSearchRepository;

import java.util.List;

@Service
public class ScbWebFixedSearchService {

    @Autowired
    ScbWeFixedSearchRepository scbWeFixedSearchRepository;

    public List search(String requestUID, ScbReportAgentSearchRequest req) {
        return scbWeFixedSearchRepository.search(requestUID, req);
    }
}
