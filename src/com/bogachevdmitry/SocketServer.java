package com.bogachevdmitry;

import com.bogachevdmitry.service.SocketServerService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class SocketServer {

    public static final int PORT = 8081;
    public static List<SocketServerService> serverList = new LinkedList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Socket is up and waiting for interaction...\n");

            while (true) {
                Socket socket = serverSocket.accept();

                try {
                    SocketServerService socketServerService = new SocketServerService(socket);
                    socketServerService.start();
                    serverList.add(socketServerService);
                } catch (IOException e) {
                    socket.close();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
