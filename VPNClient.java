import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class VPNClient {
    private static final String SECRET_KEY = "1234567890123456"; // Same key as server

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 5000); // Replace with server IP on WAN

        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Connected to VPN Server. Type messages to send:");

        while (true) {
            System.out.print("> ");
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("exit")) break;

            String encrypted = encrypt(message);
            writer.println(encrypted);

            String response = reader.readLine();
            System.out.println("Server says: " + response);
        }

        socket.close();
    }

    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
