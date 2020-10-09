package by.halatsevich.company.service.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    private static final Logger logger = LogManager.getLogger();

    private PasswordEncryption(){
    }

    public static String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String salt = "fwp";
            String finalPass = password + salt;
            byte[] bytes = digest.digest(finalPass.getBytes("UTF-8"));
            for (byte b : bytes) {
                String s = Integer.toHexString(0xff & b);
                s = (s.length() == 1) ? "0" + s : s;
                encrypted.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "Encryption algorithm was not found", e);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Unsupported sting encoding", e);
        }
        return encrypted.toString();
    }

}
