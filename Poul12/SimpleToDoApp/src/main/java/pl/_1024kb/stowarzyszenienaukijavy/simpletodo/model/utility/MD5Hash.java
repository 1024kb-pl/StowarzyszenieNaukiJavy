package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash
{ 
    public static String encode(String text) 
    { 
        try 
        { 
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            byte[] messageDigest = md.digest(text.getBytes());
  
            BigInteger notation = new BigInteger(1, messageDigest);
  
            String hashText = notation.toString(16); 
            while (hashText.length() < 32)
            { 
                hashText = "0" + hashText; 
            } 
            return hashText; 
        }  
        catch (NoSuchAlgorithmException e)
        { 
            throw new RuntimeException(e); 
        }
    }

} 
