package simple;

import MultiThreadsChatApp.ClientChat;

import javax.swing.plaf.TableHeaderUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ServerCode {

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(9999);
        ) {
            // You need to make another class to handle each client connection
            while (true) {
                System.out.println("------- Server says new Client Connected uaAlaa -------------");
                Socket clientConnected = serverSocket.accept();
                ClientSocket clientSocket = new ClientSocket(clientConnected);
                Thread thread = new Thread(clientSocket);
                thread.start();
                System.out.println("----------- I am free to receive a new client------------");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

class ClientSocket implements Runnable {
    private String serverAddress = "localhost";
    private int portNumber = 9999;
    Socket clientSocket;
    public static int objectCounter;

    public ClientSocket(Socket socket) {
        this.clientSocket = socket;
        objectCounter++;
    }

    @Override
    public void run() {
        handleSocket(clientSocket);
    }

    public void handleSocket(Socket clientSocket) {
        try {
            // we need to make things for every client uaAmer Like opening the channels uaAmer
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream writer = new PrintStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            System.out.println("new Client connected to my server !!!!");
            SmartReply smartReply = new SmartReply();
            while (true) {
                // we need to receive  message from the client because this the server side
                // [1-  Input ]
                String clientMessage = reader.readLine();

                //[2- processing ]

                String serverReply = smartReply.autoReply(clientMessage);
                //[3- output ]
                System.out.println("Client : " + clientMessage);
                writer.println(serverReply);
                System.out.println("Server : " + serverReply);
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            // You will not be able to close the reader and writer because they
            // are local scope variables inside try only
            // you don't have access to them
            // but you could close the main connection which is socket Connection
            System.out.println("Client " + ClientSocket.objectCounter + "connection closed");
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(" Error happened during closing the server connection to the client " + e.getMessage());
            }
        }
    }

}


class SmartReply {
    public String autoReply(String message) {
        switch (message) {
            case "welcome":
            case "hello":
            case "hi":
            case "Welcome":
            case "hello hello":
                System.out.println("you are welcome ");
            case "time":
            case "date":
                System.out.println(new Date());

        }
        return message;
    }
}
