package MultiThreadsChatApp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
    final private String IP_SERVER_ADDRESS = "192.168.1.6";
    final private int PORT_NUMBER = 7788;

    public void clientChatConnection() {
        try {
            // you will need to open Socket on the client Side first
            Socket clientSocket = new Socket(IP_SERVER_ADDRESS, PORT_NUMBER);

            // we will open streams uaAlaa one for sending and another one for recieving
            PrintStream writer = new PrintStream(clientSocket.getOutputStream());

            // This for reading info from server
            InputStream fromServerToClient = clientSocket.getInputStream();
            Reader reader = new InputStreamReader(fromServerToClient);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Scanner scanner = new Scanner(System.in);


            // You could put this code in the thread or single thread with try and catch statements
            //we need to recieve information from the server
            new Thread(
                    () -> {
                        try {
                            String serverResponse;
                            while ((serverResponse = bufferedReader.readLine()) != null) {
                                System.out.println(serverResponse);
                            }
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    }
            );


            //main thread to send messages to server
            while (true) {
                String message = scanner.nextLine();
                writer.println(message);
            }

        } catch (IOException exception) {
            System.out.println("Error ClientChat " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        ClientChat clientChat=new ClientChat();
        clientChat.clientChatConnection();
    }
}
