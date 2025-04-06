import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class VPNServer {
    private static final String SECRET_KEY = "1234567890123456"; // 16-char key for AES

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("VPN Server started. Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected from: " + socket.getInetAddress());

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        String encryptedMessage;
        while ((encryptedMessage = reader.readLine()) != null) {
            String decrypted = decrypt(encryptedMessage);
            System.out.println("Encrypted: " + encryptedMessage);
            System.out.println("Decrypted: " + decrypted);
            writer.println("Server received: " + decrypted);
        }

        socket.close();
        serverSocket.close();
    }

    public static String decrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(data);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
