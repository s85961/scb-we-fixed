package th.co.scb.restapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReportVote {
    private Integer categoryID;
    private Integer reportID;
    private Integer voteUserID;
    private Integer voteAmount;
}
