package th.co.scb.restapi.model;

import lombok.Data;

@Data
public class ScbReportAgentSearchRequest {
    private String categoryID;
    private Double lat;
    private Double lng;
    private Double inAreaKM;
}
