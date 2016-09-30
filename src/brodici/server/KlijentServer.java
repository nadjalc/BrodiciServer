/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brodici.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadja
 */
public class KlijentServer{

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String[] lokacije;
    

    public KlijentServer(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    
    public void ucitajLokacije() {
        System.out.println("Metoda");
        try {
            String brodici = in.readLine();
            System.out.println(brodici);
            lokacije = brodici.split("#");
            
            
        } catch (IOException ex) {
            Logger.getLogger(KlijentServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    String igraj() {
//        out.println("Pucaj!");
//        try {
//            return in.readLine(); //dobije lokaciju gde je gadjano
//        } catch (IOException ex) {
//            Logger.getLogger(KlijentServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "";
//    }

    boolean proveri(String rezultat) {
        for (int i = 0; i < lokacije.length; i++) {
            if(lokacije[i].equals(rezultat)){
                return true;
            }
        }
        return false;
    }
    
}
