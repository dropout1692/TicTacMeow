package wtf.dpt.tictacmeow.backend.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import wtf.dpt.tictacmeow.backend.dtos.AuthDTO;

public class TokenGenerator {

    private static final String SALT = "Z16dBjM#Czvc_Q3Yd1q-";

    public static String generate(AuthDTO authDTO){

        String preHash = String.format("%s:%s:%s",
                SALT,
                authDTO.getUsername(),
                authDTO.getPassword()
            );

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(preHash.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for(byte b : digest){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ignored) {
            //ignored
            return null;
        }
    }
}
