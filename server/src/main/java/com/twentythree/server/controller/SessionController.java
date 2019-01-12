package com.twentythree.server.controller;

import com.twentythree.server.constant.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SessionController {
    private ServerSocket ss;
    private Boolean stopped;

    public void initialize() {
        stopped = false;

        new Thread(() -> {
            try {
                ss = new ServerSocket(Network.PORT);

                while (!stopped) {
                    Socket spOne = ss.accept();

                    System.out.println("Player one connected.");

                    Socket spTwo = ss.accept();

                    System.out.println("Player two connected.");

                    new Thread(new Session(spOne, spTwo)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        stopped = true;
    }
}
