package th.co.scb.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.co.scb.restapi.model.ScbReportAddVoteRequest;
import th.co.scb.restapi.model.ScbReportAddVoteResponse;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;
import th.co.scb.restapi.service.ScbWeFixedService;
import th.co.scb.restapi.service.ScbWebFixedAddVoteService;

@Slf4j
@RestController
@RequestMapping("/api/scb-we-fixed")
public class ScbWeFixedController {

    @Autowired
    private ScbWeFixedService webFixedService;

    @Autowired
    private ScbWebFixedAddVoteService addVoteService;

    @PostMapping("/v1/report/save")
    public ScbReportSaveResponse reportSave(@RequestHeader String requestUID,
                                            @RequestBody ScbReportSaveRequest scbReportSaveRequest) {

        webFixedService.reportSave(scbReportSaveRequest);
        return new ScbReportSaveResponse("0000", "SUCCESS");
    }

    @PostMapping("/v1/report/addVote")
    public ScbReportAddVoteResponse reportSave(@RequestHeader String requestUID,
                                               @RequestBody ScbReportAddVoteRequest reportAddVoteRequest) {


        return addVoteService.reportAddVote(reportAddVoteRequest);
    }

}
