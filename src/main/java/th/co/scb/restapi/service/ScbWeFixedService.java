package th.co.scb.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.scb.restapi.model.ReportSaveRequest;
import th.co.scb.restapi.model.ReportSaveResponse;
import th.co.scb.restapi.model.User;
import th.co.scb.restapi.model.UserResponse;
import th.co.scb.restapi.repository.ScbWeFixedRepository;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class ScbWeFixedService {

    @Autowired
    private ScbWeFixedRepository weFixedRepository;

    public ReportSaveResponse reportSave(ReportSaveRequest reportSaveRequest) {

        int save = weFixedRepository.save(reportSaveRequest);

        if (save == 0) {
            return new ReportSaveResponse("0001", "ERROR");
        }

        return new ReportSaveResponse("0000", "SUCCESS");
    }

    public UserResponse user(User user) {
        if (user.getPassword() != null) {
            String encryptPassword = encrypt(user.getPassword());
            User userByAgent = weFixedRepository.findUserByAgent(user.getEmail(), encryptPassword);
            if (userByAgent == null) {
                return new UserResponse("0001", "ERROR", null);
            }
            return new UserResponse("0000", "SUCCESS", userByAgent);
        }
        User u = weFixedRepository.findUserBySocialId(user.getSocialId());
        if (u == null) {
            User userAdd = weFixedRepository.user(user);
            if (userAdd == null) {
                return new UserResponse("0001", "ERROR", null);
            } else {
                return new UserResponse("0000", "SUCCESS", userAdd);
            }
        } else {
            return new UserResponse("0000", "SUCCESS", u);
        }
    }

    private static final String SECRET_KEY = "SECREY_KEY_HUAWEI_SCB";
    private static final String SALT = "SALT_SCB_HUAWEI";

    public static String encrypt(String strToEncrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
}
