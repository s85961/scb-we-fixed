package th.co.scb.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.co.scb.restapi.model.ReportSaveResponse;
import th.co.scb.restapi.model.ScbReportAgentSaveRequest;
import th.co.scb.restapi.service.ScbWebFixedAgentService;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/scb-we-fixed")
public class ScbWeFixedAgentController {

    @Autowired
    private ScbWebFixedAgentService webFixedService;

    @GetMapping("/v1/checkStatus")
    public ReportSaveResponse checkStatus() {

        return new ReportSaveResponse("0000", "SUCCESS");
    }

    @PostMapping("/v1/agentUpdate")
    public ReportSaveResponse agentUpdate(@RequestHeader String requestUID,
                                          @RequestBody ScbReportAgentSaveRequest req) {

        return webFixedService.reportSave(requestUID, req);
    }

    @PostMapping("/v1/reviewUpdate")
    public ReportSaveResponse agentUpdateAfterReview(@RequestHeader String requestUID,
                                                     @RequestBody ScbReportAgentSaveRequest req) {

        return webFixedService.reviewSave(requestUID, req);
    }
}
