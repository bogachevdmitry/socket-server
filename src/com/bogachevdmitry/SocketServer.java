package com.bogachevdmitry;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        final int PORT = 8081;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Socket is up and waiting for interaction...\n");
            Socket socket = serverSocket.accept();

            try (DataInputStream in = new DataInputStream (socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                String message;
                while (true) {
                    message = in.readUTF();

                    if (message.equalsIgnoreCase("disconnect")) {
                        break;
                    }

                    System.out.printf("I've got a message %s from client!\n", message);
                    System.out.println("Sending back the same to you...");
                    out.writeUTF(message);
                    out.flush();
                    System.out.println("Send me something else!\n");
                }

            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
