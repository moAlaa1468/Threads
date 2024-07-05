package AlaaMultiThreadChatServerTalks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSide {
    private final static int portNumber = 1234;
    private final static String serverAddress = "localhost";


    public static void main(String[] args) {
        try (
                Socket socket = new Socket(serverAddress, portNumber);
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in);
        ) {


            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from server: " + e.getMessage());
                }
            }).start();


            while (true) {
                System.out.println("Connected to Server !!!");
                // By this You could send message to the server
                System.out.println("Enter the message that you need to send to tthe server : ");
                String userInput = scanner.nextLine();
                writer.println(userInput);
                System.out.println("================");


            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
