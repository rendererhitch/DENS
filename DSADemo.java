import java.security.*;
import java.util.Base64;

public class DSADemo {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(pair.getPrivate());
        String message = "This is a message.";
        sign.update(message.getBytes());
        byte[] signature = sign.sign();
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        sign.initVerify(pair.getPublic());
        sign.update(message.getBytes());
        boolean isVerified = sign.verify(signature);
        System.out.println("Signature Verified: " + isVerified);
    }
}
