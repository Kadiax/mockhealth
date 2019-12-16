package lifeprotect.mock.datamock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServer {
    private ServerSocket srv;

    MockServer(int port){
        try {
            srv = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Error port");
            e.printStackTrace();
        }
    }

    public void go() {
        System.out.println("server start");

        Socket sck;
        while(true) {
            try {
                System.out.println("waiting connections");
                sck = srv.accept();
                System.out.println("connection client");

                DataInputStream input = new DataInputStream(sck.getInputStream());
                DataOutputStream output = new DataOutputStream(sck.getOutputStream());

                System.out.println("traitement");
                String message = input.readUTF();
                historic(message);
                sck.close();

                System.out.println("fermeture connexion et serveur");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void historic(String message) {
        String[] tab = message.split("-");
        String strapid = tab[0];
        String[] values = tab[2].split(";");
    }


    public static void main(String[] args) {
        MockServer serveur = new MockServer(2008);
        serveur.go();
    }
}
