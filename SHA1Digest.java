import java.security.MessageDigest;

public class SHA1Digest {
    public static void main(String[] args) throws Exception {
        String message = "Hello SHA-1";
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(message.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("SHA-1 Hash: " + sb.toString());
    }
}
