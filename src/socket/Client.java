package socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    private DatagramSocket ds;
    private byte[] buffer;
    private Scanner sc;
    private InetAddress address;
    private int port;

    public Client() {
        try {
            ds = new DatagramSocket();
            buffer = new byte[512];
            sc = new Scanner(System.in);
            //communicateKeyboard();
            //start();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to communicate between client and server
     * @param msgList list of messages to communicate
     */
    public void communicate(ArrayList<String> msgList) {
        try {
            //creation of a signal to start conversation
            String signal = "hello";

            //creation of data packet
            DatagramPacket dp = new DatagramPacket(signal.getBytes("ascii"), signal.length(), InetAddress.getLocalHost(), 8000);
            //sending of the message
            ds.send(dp);

            //creation of packet for answer
            DatagramPacket dpresponse = new DatagramPacket(buffer, buffer.length);
            ds.receive(dpresponse);
            System.out.println(new String(dp.getData()) + "\nAdresse Ip machine : " + dp.getAddress() + "\nNuméro de port : " + dp.getPort());
            //System.out.println(new String(dpreponse.getData()));

            //until all messages are sent
            while (!msgList.isEmpty()) {
                try {
                    String message = msgList.get(0);
                    //Creation of the Datagram to send
                    DatagramPacket dpmessage = new DatagramPacket(message.getBytes("ascii"), message.length(), dpresponse.getAddress(), dpresponse.getPort());
                    ds.send(dpmessage);

                    byte[] buffer2 = new byte[512];
                    //Receipt of the Datagram back
                    DatagramPacket dpRepServer = new DatagramPacket(buffer2, buffer2.length);
                    ds.receive(dpRepServer);
                    String S = new String(dpRepServer.getData(), "ascii");
                    //Server message display
                    System.out.println(S);
                    msgList.remove(0);
                } catch (SocketException s) {
                    s.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
