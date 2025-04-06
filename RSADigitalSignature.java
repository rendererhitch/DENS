import java.security.*;
import java.util.Base64;

public class RSADigitalSignature {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        Signature sign = Signature.getInstance("SHA256withRSA");
        String message = "RSA Signature Example";
        sign.initSign(pair.getPrivate());
        sign.update(message.getBytes());
        byte[] signature = sign.sign();

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        sign.initVerify(pair.getPublic());
        sign.update(message.getBytes());
        boolean isVerified = sign.verify(signature);
        System.out.println("Verified: " + isVerified);
    }
}
