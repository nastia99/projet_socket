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

        //Server creation
        Server LocalHost = new Server();
        Thread threadServr = new Thread(LocalHost);
        threadServr.start();

        //Conversation client1
        Client client1 = new Client();
        ArrayList<String> conv1 = new ArrayList();
        conv1.add("Bonjour, je suis le client1");
        conv1.add("Allo, Ici Houston");
        conv1.add("Est ce que vous me recevez");
        conv1.add("FIN");
        client1.communicate(conv1);

        //Conversation client 2
        Client client2 = new Client();
        ArrayList<String> conv2 = new ArrayList();
        conv2.add("Bonjour, je suis le client2 ");
        conv2.add("5/5 Houston");
        conv2.add("FIN");
        client2.communicate(conv2);

        // new Server();
        // new Client();
    }

}
