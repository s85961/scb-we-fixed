package th.co.scb.restapi.model;

import lombok.Data;

@Data
public class ReportSaveRequest {
    private String latitude;
    private String longitude;
    private String picture1;
    private String picture2;
    private String picture3;
    private int categoryId;
    private String userId;
    private String topic;
    private String description;
}
