package th.co.scb.restapi.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ScbReportSaveRequest;
import th.co.scb.restapi.model.ScbReportSaveResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ScbWebFixedService {

    public ScbReportSaveResponse reportSave(ScbReportSaveRequest reportSaveRequest) {
        List<String> pictures = reportSaveRequest.getPictures();

        String picture1 = reportSaveRequest.getPictures().get(0);
        String picture2 = reportSaveRequest.getPictures().get(1);
        String picture3 = reportSaveRequest.getPictures().get(3);

        for (String p : pictures) {
            byte[] data = Base64.decodeBase64(p);
            try (OutputStream stream = new FileOutputStream("/Users/panszy/VCH/a.png")) {
                stream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ScbReportSaveResponse("0000", "SUCCESS");
    }
}
