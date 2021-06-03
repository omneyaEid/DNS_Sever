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
        try {
            DatagramSocket serverSocket = new DatagramSocket(1235);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            byte[] sendData1 = new byte[1024];
            String[] URLroot = new String[]{"www.yahoo.com", "www.google.com", "www.fci.com"};
            String[] IProot = new String[]{"127.0.0.113", "127.9.9.1", "192.168.10.10"};
            String[] TrueName = new String[]{"", "", ""};

            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Server is ready to communicate with any client ");
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);
                String IP = "";
                String servername = "root";
                String querry = "A";
                String TName = " True name: ";
                int count = 0;
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                for(int i = 0; i < URLroot.length; ++i) {
                    String TNameTemp;
                    if (sentence.trim().equals(URLroot[i])) {
                        System.out.println(IProot[i]);
                        IP = IProot[i];
                        TNameTemp = TName + TrueName[i];
                        if (TNameTemp.trim().equals(TName.trim())) {
                            TName = "";
                        } else {
                            TName = TNameTemp;
                        }

                        ++count;
                    } else if (sentence.trim().equals(IProot[i])) {
                        System.out.println(URLroot[i]);
                        IP = sentence;
                        sentence = URLroot[i];
                        TNameTemp = TName + TrueName[i];
                        if (TNameTemp.trim().equals(TName.trim())) {
                            TName = "";
                        } else {
                            TName = TNameTemp;
                        }

                        ++count;
                        querry = "CNAME";
                    }
                }

                String sendDatas;
                if (count == 0) {
                    System.out.println("not found");
                    IP = "not found";
                    sendDatas = "not found";
                } else {
                    sendDatas = "URL= " + sentence.trim() + "  IP Address= " + IP + TName + " Querry: " + querry + "  Server name: " + servername;
                }

                byte[] responsebyte = new byte[4000];
                responsebyte = sendDatas.trim().getBytes();
                System.out.println(sendDatas.trim());
                DatagramPacket serverPacket = new DatagramPacket(responsebyte, responsebyte.length, IPAddress, port);
                serverSocket.send(serverPacket);
                receiveData = new byte['\uffff'];
            }
        } catch (Exception var21) {
            System.out.println("Problem with server socket");
        }
    }
}
