package pl._1024kb.stowarzyszenienaukijavy.simpletodo.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PBKDF2Hash {
    private final static String SALT = "1234";
    private final static int ITERATIONS = 10000;
    private final static int KEY_LENGTH = 512;

    public static String encode(String password) {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = SALT.getBytes();

        byte[] hashedBytes = hashPassword(passwordChars, saltBytes);

        return Hex.encodeHexString(hashedBytes);
    }

    private static byte[] hashPassword(final char[] password, final byte[] salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

} 
