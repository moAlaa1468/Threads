package MultiThreadsChatApp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    private PrintWriter toClientPrinter;
    private Scanner scanner;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    // And this will run for every Thread
    @Override
    public void run() {
        // This will run for every Client uaAmer
        this.handleSocket();
    }

    public void handleSocket() {
        // This will run for every Client uaAmer
        try {
            // This line server will write to a file You could make the server send data back to client very easy Way
            writer = new PrintWriter(new FileWriter("chat.txt", true), true);
            // From this line the server will send the message back to the client Thanaks Mohamed Alaa amer is here
            toClientPrinter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            // We will read from the file and then we will send these messages to the server
            String message;
            scanner = new Scanner(System.in);
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                writer.println("Client " + clientSocket.getInetAddress() + ": " + message);
                // You could open Scanner to send some information back to the client yaAmer
                System.out.println("Enter message to be send to the client : ");
                String serverInput = scanner.nextLine();
                toClientPrinter.println("Client One : " + serverInput);

            }


            // we need to take input from server keyboard and send it to the client How would you make this
        } catch (IOException ex) {
            System.out.println("Error handling the client : " + ex.getMessage());
        } finally {
            // You need to close all your resource
            // Hint don't use Try with resource here uaamer This is the wrong
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
                if (clientSocket != null) clientSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}



/*
* import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            writer = new PrintWriter(new FileWriter("chat.txt", true), true); // Append mode
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                writer.println("Client " + clientSocket.getInetAddress() + ": " + message);
            }
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

*
* */