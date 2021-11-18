package th.co.scb.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.model.ScbReportAgentSearchRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;
import th.co.scb.restapi.repository.ScbWeFixedSearchRepository;
import th.co.scb.restapi.service.ScbWebFixedAgentService;
import th.co.scb.restapi.service.ScbWebFixedSearchService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/scb-we-fixed")
public class ScbWeFixedSearchController {

    @Autowired
    private ScbWebFixedSearchService webFixedSearchService;

    @PostMapping("/v1/searchReport")
    public List searchReport(@RequestHeader String requestUID,
                             @RequestBody ScbReportAgentSearchRequest req) {

        return webFixedSearchService.search(requestUID, req);
    }
}
