/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brodici.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.PortableInterceptor.INACTIVE;

/**
 *
 * @author Nadja
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    ServerSocket osluskivac;
    KlijentServer k2;
    KlijentServer k1;

    public Server() {
    }

    public static void main(String[] args) {
        try {
            Server s = new Server();
            s.osluskivac = new ServerSocket(9090);
            System.out.println("Server je pokrenut");

            Socket socket1 = s.osluskivac.accept();
            System.out.println("Prikljucio se prvi klijent");
            s.k1 = new KlijentServer(socket1);
            s.k1.ucitajLokacije();

            Socket socket2 = s.osluskivac.accept();
            System.out.println("Prikljucio se drugi klijent");
            s.k2 = new KlijentServer(socket2);
            s.k2.ucitajLokacije();

            s.k1.out.println("Pucaj!");
            s.k2.out.println("Cekaj");

            while (true) {
                while (true) {
                    String pucanj = s.k1.in.readLine();
                    if (s.k2.proveri(pucanj)) {
                        s.k1.out.println("pogodio," + pucanj);
                        s.k2.out.println("pogodjen," + pucanj);
                    } else {
                        s.k1.out.println("promasio," + pucanj);
                        s.k2.out.println("promasen," + pucanj);
                        break;
                    }
                }
                while (true) {
                    String pucanj2 = s.k2.in.readLine();
                    if (s.k1.proveri(pucanj2)) {
                        s.k2.out.println("pogodio," + pucanj2);
                        s.k1.out.println("pogodjen," + pucanj2);
                    } else {
                        s.k2.out.println("promasio," + pucanj2);
                        s.k1.out.println("promasen," + pucanj2);
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Server se gasi");
        }
    }
}
