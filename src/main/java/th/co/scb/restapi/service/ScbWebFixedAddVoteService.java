package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ScbReportAddVoteRequest;
import th.co.scb.restapi.model.ScbReportAddVoteResponse;
import th.co.scb.restapi.repository.ScbWeFixedRepository;

@Service
public class ScbWebFixedAddVoteService {

    @Autowired
    private ScbWeFixedRepository scbWeFixedRepository;

    public ScbReportAddVoteResponse reportAddVote(ScbReportAddVoteRequest reportAddVoteRequest) {
        if (null != reportAddVoteRequest.getReportId() && null != reportAddVoteRequest.getVoteUserId() && null !=  reportAddVoteRequest.getCategoryID()){
            boolean isSuccess =  scbWeFixedRepository.insertAddVote(reportAddVoteRequest);
            if (isSuccess){
                //int voteAmount = scbWeFixedRepository.inqAddVote(reportAddVoteRequest);
                return new ScbReportAddVoteResponse("0000", "SUCCESS",1);
            }else {
                return new ScbReportAddVoteResponse("0001", "Can not add vote",null);
            }
        }
        return new ScbReportAddVoteResponse("0001", "Request is required",null);
    }
}
