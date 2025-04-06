import java.io.*;
import java.net.*;
import java.util.Scanner;

public class VTPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to the server");

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                output.println(message);
                System.out.println("Server: " + input.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
