package th.co.scb.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.co.scb.restapi.model.*;
import th.co.scb.restapi.service.ScbWeFixedService;
import th.co.scb.restapi.service.ScbWebFixedAddVoteService;

@Slf4j
@RestController
@RequestMapping("/api/scb-we-fixed")
@CrossOrigin()
public class ScbWeFixedController {

    @Autowired
    private ScbWeFixedService webFixedService;

    @Autowired
    private ScbWebFixedAddVoteService addVoteService;

    @PostMapping("/v1/report/save")
    public ReportSaveResponse reportSave(@RequestHeader String requestUID,
                                         @RequestBody ReportSaveRequest reportSaveRequest) {

        webFixedService.reportSave(reportSaveRequest);
        return new ReportSaveResponse("0000", "SUCCESS");
    }

    @PostMapping("/v1/user")
    public UserResponse user(@RequestHeader String requestUID,
                             @RequestBody User user) {

        return webFixedService.user(user);
    }

    @PostMapping("/v1/report/addVote")
    public ScbReportAddVoteResponse reportSave(@RequestHeader String requestUID,
                                               @RequestBody ScbReportAddVoteRequest reportAddVoteRequest) {


        return addVoteService.reportAddVote(reportAddVoteRequest);
    }

}
