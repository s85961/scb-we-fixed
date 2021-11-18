package th.co.scb.restapi.model;

import lombok.Data;

import java.util.List;

@Data
public class ScbReportSaveRequest {
    private String latitide;
    private String longitude;
    private List<String> pictures;
}
