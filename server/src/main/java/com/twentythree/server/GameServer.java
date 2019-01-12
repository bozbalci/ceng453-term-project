package com.twentythree.server;

import com.twentythree.server.controller.SessionController;

public class GameServer {
    public static void main(String[] args) {
        SessionController sc = new SessionController();
        sc.initialize();
    }
}
