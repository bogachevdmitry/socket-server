package com.bogachevdmitry.service;

import java.io.*;
import java.net.Socket;

public class SocketServerService extends Thread {

    private final DataInputStream in;
    private final DataOutputStream out;

    public SocketServerService(Socket socket) throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String message;
        try {

            while(true) {
                message = in.readUTF();
                if (message.equalsIgnoreCase("q:")) {
                    break;
                }

                System.out.printf("I've got a message `%s` from client!\n", message);
                System.out.println("Sending back the same to the client...");
                this.send(message);
            }

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private void send(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
