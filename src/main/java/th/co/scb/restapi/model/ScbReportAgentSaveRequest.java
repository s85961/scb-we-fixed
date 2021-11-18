package th.co.scb.restapi.model;

import lombok.Data;

import java.util.List;

@Data
public class ScbReportAgentSaveRequest {
    private String categoryID;
    private Integer reportID;
    private String fixDescription;
    private Boolean fixStatus;
    private Integer userID;
    private Boolean reportAccept;
}
