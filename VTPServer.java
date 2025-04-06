import java.io.*;
import java.net.*;

public class VTPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {

            System.out.println("Server is connected to port 12345");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String message;
                while ((message = input.readLine()) != null) {
                    System.out.println("Received: " + message);
                    output.println("Acknowledged: " + message);
                }

                socket.close(); // Optional: close client connection if done
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
