package simple;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientCode {

    private static String serverAddress = "localhost";
    private static int portNumber = 9999;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(serverAddress, portNumber);
                // we need to create some channels uaamer
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter write = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                // we need to create Scanner to send some data to the server side
                Scanner scanner = new Scanner(System.in);

        ) {

            System.out.println(" This is my information : " + socket.getInetAddress() + "port is :" + portNumber);
            while (true) {
                System.out.println("User Need to type some words to be sent to server :");
                //[ 1- Input  ]
                String clientInput = scanner.nextLine();
                write.println(clientInput);
                // we need to receive the sever response
                //[2- processing ]
                String serverResponse = reader.readLine();

                //[3- outPut ]
                System.out.println("Server : " + serverResponse);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
