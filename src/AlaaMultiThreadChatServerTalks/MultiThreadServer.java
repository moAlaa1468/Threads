package AlaaMultiThreadChatServerTalks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiThreadServer {
    // first we need to create server connection
    private final static int portNumber = 1234;
    public static List<HandleSocket> clients = new ArrayList<HandleSocket>();

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {

            // Thread to read server's input and broadcast to all clients
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    String serverMessage = scanner.nextLine();
                    broadcastMessage("Server: " + serverMessage);
                }
            }).start();
            while (true) {
                System.out.println(" ---- Server is started -----");
                Socket clientSocket = serverSocket.accept();


                HandleSocket handleSocket = new HandleSocket(clientSocket);
                new Thread(handleSocket).start();
            }
        } catch (IOException e) {
            e.getMessage();
        }


    }




    public static void broadcastMessage(String message) {
        for (HandleSocket client : clients) {
            client.sendMessage(message);
        }
    }

    static class HandleSocket implements Runnable {
        //    private final static int portNumber=1234;
        //    private final static String serverAddress="localhost";
        private Socket socket;
        private PrintWriter writer;
        private PrintWriter fileWriter;
        private BufferedReader reader;

        HandleSocket(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // to open the channels
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                fileWriter = new PrintWriter(new FileWriter("chat.txt", true), true);
                writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Message Recieved is : " + message);
                    fileWriter.println("Client " + socket.getInetAddress() + message);


                }

            } catch (IOException e) {
                e.getMessage();
            } finally {
                try {
                    reader.close();
                    socket.close();
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }

}


///====================================


