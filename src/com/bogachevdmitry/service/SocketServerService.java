package com.bogachevdmitry.service;

import java.io.*;
import java.net.Socket;

public class SocketServerService extends Thread {

    private final BufferedReader in;
    private final BufferedWriter out;

    public SocketServerService(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String message;
        try {

            while(true) {
                message = in.readLine();
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
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
