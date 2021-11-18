package th.co.scb.restapi.model;

import lombok.Data;

@Data
public class ScbReportAgentSearchRequest {
    private String categoryID;
    private Double latitude;
    private Double longitude;
    private Double inAreaKM;
}
