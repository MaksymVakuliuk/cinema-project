package com.dev.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {
    private static final Logger logger = Logger.getLogger(HashUtil.class);

    public byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[15];
        random.nextBytes(salt);
        return salt;
    }

    public String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b : digest) {
                hashPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return hashPassword.toString();
    }

    public boolean isValidPassword(String hashPassword, String password, byte[] salt) {
        return hashPassword(password, salt).equals(hashPassword);
    }
}
