package by.halatsevich.company.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The class represents password encryption util.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class PasswordEncryption {
    private static final Logger logger = LogManager.getLogger(PasswordEncryption.class);
    private static final String ALGORITHM = "SHA-256";
    private static final String SALT = "fwp";
    private static final String PATTERN = "0";

    private PasswordEncryption() {
    }

    /**
     * Encrypt password.
     *
     * @param password the password
     * @return the encrypted password
     */
    public static String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            String finalPass = password + SALT;
            byte[] bytes = digest.digest(finalPass.getBytes(StandardCharsets.UTF_8));
            for (byte b : bytes) {
                String s = Integer.toHexString(0xff & b);
                s = (s.length() == 1) ? PATTERN + s : s;
                encrypted.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "Encryption algorithm was not found", e);
        }
        return encrypted.toString();
    }
}
