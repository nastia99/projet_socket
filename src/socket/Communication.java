package socket;

import java.io.IOException;
import java.net.*;

public class Communication implements Runnable {

    DatagramPacket dp;

    private int id;
    private DatagramSocket ds;
    private byte[] buffer;
    private InetAddress address;
    private int port;


    public Communication(InetAddress address, int port){
        try {
            this.ds = new DatagramSocket();
            this.address = address;
            this.port = port;

            String data = "Connexion bien établie";
            this.buffer = data.getBytes();
            DatagramPacket dp = new DatagramPacket(this.buffer, this.buffer.length, this.address, this.port);
            this.ds.send(dp);

            Thread thread = new Thread(this);
            thread.start();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * method to create the connection between client and server
     */
    @Override
    public void run() {
        boolean end = false;
        System.out.println("Communication établie");

        while (!end){
            try {
                this.buffer = new byte[512];
                DatagramPacket dp = new DatagramPacket(this.buffer, this.buffer.length);
                this.ds.receive(dp);
                dp = new DatagramPacket(dp.getData(),dp.getLength(), this.address, this.port);

                if(new String(dp.getData(), dp.getOffset(), dp.getLength()).equals("FIN")){ end = true; }

                this.ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Communication terminée");
        this.ds.close();
    }
}
