//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        new Scanner(System.in);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence;
        do {
            sentence = inFromUser.readLine();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1234);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String serverMSG = new String(receivePacket.getData());
            System.out.println("FROM SERVER: " + serverMSG);
            receiveData = new byte[4000];
        } while(!sentence.equals("exit"));

        clientSocket.close();
    }
}