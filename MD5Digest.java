import java.security.MessageDigest;


public class MD5Digest {
    public static void main(String[] args) throws Exception {
        String message = "Hello MD5";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(message.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("MD5 Hash: " + sb.toString());
    }
}
