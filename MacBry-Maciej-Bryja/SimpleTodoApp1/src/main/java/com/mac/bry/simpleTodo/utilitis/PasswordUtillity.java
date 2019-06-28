package com.mac.bry.simpleTodo.utilitis;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class PasswordUtillity {
	   
    public static String getHashedPassword(String passwordToHash) {
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
           
        } catch (NoSuchAlgorithmException e) {
           
            e.printStackTrace();
        }
       
        return null;
    }
   
}