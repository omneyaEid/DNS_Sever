//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.company;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(1237);
        byte[] sendData = new byte[1024];
        byte[] sendData1 = new byte[1024];
        String[] URLAuth = new String[]{"www.yahoo.com", "www.google.com", "www.fci.com", "gaia.cs.umass.edu"};
        String[] IPAuth = new String[]{"127.0.0.113", "127.9.9.1", "192.168.10.10", "128.119.245.12"};
        String[] TrueName = new String[]{"", "", "", ""};

        while(true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Server is ready to communicate with any client ");
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            String IP = "";
            String querry = "  Querry: ";
            String servername = "Authoritative";
            String TName = " True name: ";
            int count = 0;
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            for(int i = 0; i < URLAuth.length; ++i) {
                String TNameTemp;
                if (sentence.trim().equals(URLAuth[i])) {
                    System.out.println(IPAuth[i]);
                    IP = IPAuth[i];
                    querry = querry + "A";
                    TNameTemp = TName + TrueName[i];
                    if (TNameTemp.trim().equals(TName.trim())) {
                        TName = "";
                    } else {
                        TName = TNameTemp;
                    }

                    ++count;
                } else if (sentence.trim().equals(IPAuth[i])) {
                    System.out.println(URLAuth[i]);
                    IP = sentence.trim();
                    sentence = URLAuth[i];
                    TNameTemp = TName + TrueName[i];
                    if (TNameTemp.trim().equals(TName.trim())) {
                        TName = "";
                    } else {
                        TName = TNameTemp;
                    }

                    ++count;
                    querry = querry + "CNMAE";
                }
            }

            String sendDatas;
            if (count == 0) {
                System.out.println("not found");
                sendDatas = "Does not exist";
                IP = "not found";
            } else {
                sendDatas = "URL= " + sentence.trim() + " IP: " + IP + TName + querry + " Server Name: " + servername;
            }

            byte[] responsebyte = new byte[10000];
            responsebyte = sendDatas.trim().getBytes();
            System.out.println(sendDatas.trim());
            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket serverPacket = new DatagramPacket(responsebyte, responsebyte.length, clientIP, clientPort);
            serverSocket.send(serverPacket);
        }
    }
}
