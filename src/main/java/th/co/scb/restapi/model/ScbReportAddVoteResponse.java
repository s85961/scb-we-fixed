package th.co.scb.restapi.model;

import lombok.Value;

@Value
public class ScbReportAddVoteResponse {
    private String code;
    private String message;
    private Integer voteAmount;
}
