package com.twentythree.server.controller;

import com.twentythree.server.constant.Network;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Session implements Runnable {
    private Socket spOne, spTwo;
    private Long posOne, posTwo;

    public Session(Socket spOne, Socket spTwo) {
        System.out.println("Initializing new session...");

        this.spOne = spOne;
        this.spTwo = spTwo;

        posOne = 0L;
        posTwo = 0L;
    }

    @Override
    public void run() {
        System.out.println("Session started. Attempting to start game...");

        boolean finished = false;
        boolean pOneDead, pTwoDead;

        try {
            ObjectOutputStream toSpOne = new ObjectOutputStream(spOne.getOutputStream());
            ObjectInputStream fromSpOne = new ObjectInputStream(spOne.getInputStream());

            ObjectOutputStream toSpTwo = new ObjectOutputStream(spTwo.getOutputStream());
            ObjectInputStream fromSpTwo = new ObjectInputStream(spTwo.getInputStream());

            String started = Network.CMD_START.toString() + ";0";
            toSpOne.writeObject(started);
            toSpTwo.writeObject(started);

            System.out.println("Starting game...");

            while (!finished) {
                String[] parts;
                String inpOne, inpTwo;
                HashMap<String, Long> cpOne = new HashMap<>(), cpTwo = new HashMap<>();

                inpOne = (String) fromSpOne.readObject();
                parts = inpOne.split(";");
                cpOne.put("type", Long.parseLong(parts[0]));
                cpOne.put("param", Long.parseLong(parts[1]));

                inpTwo = (String) fromSpTwo.readObject();
                parts = inpTwo.split(";");
                cpTwo.put("type", Long.parseLong(parts[0]));
                cpTwo.put("param", Long.parseLong(parts[1]));

                if (cpOne.get("type").equals(Network.CMD_POSITION) ||
                    cpOne.get("type").equals(Network.CMD_DEATH)) {
                    toSpTwo.writeObject(inpOne);
                    System.out.println("Sent to P2: " + inpOne);
                }

                if (cpTwo.get("type").equals(Network.CMD_POSITION) ||
                    cpTwo.get("type").equals(Network.CMD_DEATH)) {
                    toSpOne.writeObject(inpTwo);
                    System.out.println("Sent to P1: " + inpTwo);
                }

                pOneDead = cpOne.get("type").equals(Network.CMD_DEATH);
                pTwoDead = cpTwo.get("type").equals(Network.CMD_DEATH);

                if (pOneDead && pTwoDead)
                    finished = true;
            }
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
