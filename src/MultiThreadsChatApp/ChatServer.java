package MultiThreadsChatApp;

import com.sun.source.tree.CompoundAssignmentTree;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    //    private static final String SERVER_IP_ADDRESS = "192.168.1.6";
    private static final int PORT_NUMBER = 7788;

    // we need to create function for server
    public static void establishServerSettings() {
        try (
                ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        ) {
            System.out.println("-------- Sever Started on port " + PORT_NUMBER + "------------");
            // YOU NEED to repead the code for single client uaAmer
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // You need to locate server information
                System.out.println("new Client connected uaAmer " + clientSocket.getInetAddress());

                // you need to create class to handler Class every client uaamer
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server : "+e.getMessage());
        }
    }

    public static void main(String[]args){
//        ChatServer chatServer=new ChatServer();
        ChatServer.establishServerSettings();
    }
}
