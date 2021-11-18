package th.co.scb.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;

@Slf4j
@RestController
@RequestMapping("/api/scb-we-fixed")
public class ScbWebFixedController {

    @PostMapping("/v1/report/save")
    public ScbReportSaveResponse reportSave(@RequestHeader String requestUID,
                                            @RequestBody ScbReportSaveRequest scbReportSaveRequest) {


        return new ScbReportSaveResponse("0000", "SUCCESS");
    }
}
