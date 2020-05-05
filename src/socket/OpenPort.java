package socket;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class OpenPort {

    /**
     * function that tests whether a port is open or closed
     * @param min low range port
     * @param max upper range port
     * @returns the list of occupied ports
     */
    public static ArrayList<Integer> checkOpenPort(int min, int max) throws SocketException {

        ArrayList<Integer> occupiedPorts = new ArrayList<>();

        for (int i = min ;  i <= max; i++) {
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(i);
                System.out.println("Le port " + i + " est ouvert");
                ds.close();
            } catch (Exception e) {
                occupiedPorts.add(i);
                System.out.println("Le port " + i + " est fermé");
            }
        }
        return occupiedPorts;
    }

    /**
     * function that tests whether a port is open or closed
     * @param min low range port
     * @param max upper range port
     * @return a list of booleans for occupied and unoccupied ports
     */
    public static ArrayList<Boolean> openPorts(int min, int max){
        List<Boolean> portStatus = new ArrayList<>();
        for (int i=min; i<=max; i++){
            try {
                DatagramSocket ds = new DatagramSocket(i);
                portStatus.add(true);
                ds.close();
            } catch (SocketException ex){
                System.out.println(i);
                portStatus.add(false);
            }
        }
        return (ArrayList<Boolean>) portStatus;
    }
}
