package com.twentythree.spaceclient.controller;

import com.twentythree.spaceclient.constants.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class MPGameController {
    private Socket socket;
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;

    public void connect() {
        try {
            socket = new Socket(Network.SERVER_HOST, Network.SERVER_PORT);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Long> receiveCommand() {
        HashMap<String, Long> result = new HashMap<>();

        try {
            String input = (String) fromServer.readObject();
            String[] parts = input.split(";");
            result.put("type", Long.parseLong(parts[0]));
            result.put("param", Long.parseLong(parts[1]));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void sendCommand(HashMap<String, Long> command) {
        String output = command.get("type").toString() + ";" + command.get("param").toString();
        try {
            toServer.writeObject(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
