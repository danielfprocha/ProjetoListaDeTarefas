package Classes;
import java.security.MessageDigest;
import java.util.Base64;

public class Hash {
    
    public static String sha256Base64 (String texto) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(texto.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verificar(String textoPuro, String hashBase64) throws Exception {
        return sha256Base64(textoPuro).equals(hashBase64);
    }
}
