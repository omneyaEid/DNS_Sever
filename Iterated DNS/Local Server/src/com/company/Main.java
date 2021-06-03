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
        DatagramSocket serverSocket = new DatagramSocket(1234);
        byte[] sendData = new byte[1024];
        byte[] sendData1 = new byte[1024];
        String[] URLLocal = new String[]{"www.yahoo.com", "www.google.com"};
        String[] IPLocal = new String[]{"127.0.0.113", "127.9.9.1"};
        String[] TrueName = new String[]{"", ""};

        while(true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Server is ready to communicate with any client ");
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            String IP = "";
            String querry = "  Querry: ";
            String servername = "Local";
            String TName = " True name: ";
            int count = 0;
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            for(int i = 0; i < URLLocal.length; ++i) {
                String TNameTemp;
                if (sentence.trim().equals(URLLocal[i])) {
                    System.out.println(IPLocal[i]);
                    IP = IPLocal[i];
                    querry = querry + "A";
                    TNameTemp = TName + TrueName[i];
                    if (TNameTemp.trim().equals(TName.trim())) {
                        TName = "";
                    } else {
                        TName = TNameTemp;
                    }

                    ++count;
                } else if (sentence.trim().equals(IPLocal[i])) {
                    System.out.println(URLLocal[i]);
                    IP = sentence.trim();
                    sentence = URLLocal[i];
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

            if (count == 0) {
                System.out.println("not found");
                IP = "not found";
            }

            String sendDatas = "URL= " + sentence.trim() + " IP: " + IP + TName + querry + " Server Name: " + servername;
            byte[] responsebyte = new byte[10000];
            DatagramPacket sendPacket;
            if (IP.equals("not found")) {
                byte[] recieveData1 = new byte[4000];
                DatagramSocket clientSocket = new DatagramSocket();
                sendPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, 1235);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket1 = new DatagramPacket(recieveData1, recieveData1.length);
                clientSocket.receive(receivePacket1);
                String rootMSG = new String(receivePacket1.getData());
                System.out.println("From root:" + rootMSG);
                recieveData1 = new byte[4000];
                DatagramPacket serverPacket;
                if (rootMSG.trim().equals("not found")) {
                    byte[] recieveData2 = new byte[4000];
                    DatagramSocket clientSocket1 = new DatagramSocket();
                    serverPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, 1236);
                    clientSocket1.send(serverPacket);
                    DatagramPacket receivePacket2 = new DatagramPacket(recieveData2, recieveData2.length);
                    clientSocket1.receive(receivePacket2);
                    String TLDMSG = new String(receivePacket2.getData());
                    System.out.println("From TLD:" + TLDMSG);
                    recieveData2 = new byte[4000];
                    if (TLDMSG.trim().equals("not found")) {
                        byte[] recieveData3 = new byte[4000];
                        DatagramSocket clientSocket2 = new DatagramSocket();
                        DatagramPacket sendPacket2 = new DatagramPacket(receiveData, receiveData.length, IPAddress, 1237);
                        clientSocket2.send(sendPacket2);
                        DatagramPacket receivePacket3 = new DatagramPacket(recieveData3, recieveData3.length);
                        clientSocket2.receive(receivePacket3);
                        String AuthMSG = new String(receivePacket3.getData());
                        System.out.println("From Authoritative:" + AuthMSG);
                        TLDMSG = AuthMSG;
                        recieveData3 = new byte[4000];
                        clientSocket2.close();
                    }

                    rootMSG = "";
                    rootMSG = TLDMSG;
                    clientSocket1.close();
                }

                responsebyte = rootMSG.trim().getBytes();
                rootMSG = null;
                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                serverPacket = new DatagramPacket(responsebyte, responsebyte.length, clientIP, clientPort);
                serverSocket.send(serverPacket);
                clientSocket.close();
            } else {
                responsebyte = sendDatas.trim().getBytes();
                System.out.println(sendDatas.trim());
                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendPacket = new DatagramPacket(responsebyte, responsebyte.length, clientIP, clientPort);
                serverSocket.send(sendPacket);
            }

            responsebyte = new byte[4000];
            receiveData = new byte[4000];
        }
    }
}
