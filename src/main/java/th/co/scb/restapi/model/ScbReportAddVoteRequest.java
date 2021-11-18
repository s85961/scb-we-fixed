package th.co.scb.restapi.model;

import lombok.Data;

@Data
public class ScbReportAddVoteRequest {
    private Integer reportId;
    private Integer voteUserId;
    private Integer categoryID;


}
