package multiThreadChatApplication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadedChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create a new thread for each client connection
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private PrintWriter toClientPrinter;
    private Scanner scanner;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            writer = new PrintWriter(new FileWriter("chat.txt", true), true); // Append mode
            toClientPrinter = new PrintWriter((new OutputStreamWriter(clientSocket.getOutputStream())));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //[1- input ]
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                writer.println("Client " + clientSocket.getInetAddress() + ": " + message);
            }

            //[2- processing ]
            String serverMessage="This is coming from server ";
            new Thread(
                    ()->{
                        System.out.println("Please enter message to be send to the client : ");
                      String input=  scanner.nextLine();
                        toClientPrinter.println(input);
                    }
            ).start();
            //[3- output ]
            System.out.println();


        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
                if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
