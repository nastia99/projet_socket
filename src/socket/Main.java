package socket;

import java.util.ArrayList;

public class Main {

    public static void main(String[] arg) throws Exception {
        //occupied ports
        ArrayList<Integer> occupiedPorts = OpenPort.checkOpenPort(5352,5355);
        System.out.println(occupiedPorts);

        //ports status
        ArrayList<Boolean> freePorts = OpenPort.openPorts(5351,5355);
        System.out.println(freePorts);


    }

}
