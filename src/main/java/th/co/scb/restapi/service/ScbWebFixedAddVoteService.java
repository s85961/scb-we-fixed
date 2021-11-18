package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ReportVote;
import th.co.scb.restapi.model.ScbReportAddVoteRequest;
import th.co.scb.restapi.model.ScbReportAddVoteResponse;
import th.co.scb.restapi.repository.ScbWeFixedRepository;

@Service
public class ScbWebFixedAddVoteService {

    @Autowired
    private ScbWeFixedRepository scbWeFixedRepository;

    public ScbReportAddVoteResponse reportAddVote(ScbReportAddVoteRequest reportAddVoteRequest) {
        boolean isSuccess = false;
        if (null != reportAddVoteRequest.getReportId() && null != reportAddVoteRequest.getVoteUserId() && null !=  reportAddVoteRequest.getCategoryID()){
            ReportVote reportVote =  scbWeFixedRepository.inqAddVote(reportAddVoteRequest);
            if (1 > reportVote.getVoteAmount() ){
                 isSuccess =  scbWeFixedRepository.insertAddVote(reportAddVoteRequest);
            }else {
                isSuccess = true;
            }
            if (isSuccess){
                int voteAmount = scbWeFixedRepository.countVote(reportAddVoteRequest);
                return new ScbReportAddVoteResponse("0000", "SUCCESS",voteAmount);
            }else {
                return new ScbReportAddVoteResponse("0001", "Can not add vote",null);
            }
        }
        return new ScbReportAddVoteResponse("0001", "Request is required",null);
    }
}
