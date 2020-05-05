package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable {

    private DatagramSocket ds;

    public Server(){
        try {
            this.ds = new DatagramSocket(8000);
            Thread thread = new Thread(this);
            thread.start();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    /**
     * method to run the server
     */
    @Override
    public void run() {
        System.out.println("Le serveur est prêt a reçevoir les demandes de connexions :");
        while(true) {
            try {
                byte[] buffer = new byte[512];
                DatagramPacket dpp = new DatagramPacket(buffer, buffer.length);
                System.out.println("Wainting ...");
                this.ds.receive(dpp);
                System.out.println("Information received");
                String S = new String(dpp.getData(), "ascii");

                if(S.charAt(0) == 'h' && S.charAt(1) == 'e' && S.charAt(2) == 'l' && S.charAt(3) == 'l' && S.charAt(4) == 'o') {
                    new Thread(new Communication(dpp.getAddress(), dpp.getPort()));
                } else {
                    String rep = "Connexion impossible!";
                    DatagramPacket dp = new DatagramPacket(rep.getBytes("ascii"), rep.length(), dpp.getAddress(), dpp.getPort());
                    this.ds.send(dp);
                    System.out.println("Sending confirmation\n");
                }


            } catch (SocketException s) {
                s.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
