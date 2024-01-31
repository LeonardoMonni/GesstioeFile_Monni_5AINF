/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionefile;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monni Leonardo
 */
public class Copiatore extends Thread {
    String FileDaCopiare;
    String FileDaIncollare;

    public Copiatore(String FileDaCopiare, String FileDaIncollare) {
        this.FileDaCopiare = FileDaCopiare;
        this.FileDaIncollare = FileDaIncollare;
    }
    
    public String leggi(){
        StringBuilder sb=new StringBuilder(); 
        FileReader fr;
        int i; 
        try { 
            //1) apro il file
            fr = new FileReader(FileDaCopiare);
            //2) leggo carattere per carattere e lo stampo 
            while ((i=fr.read()) != -1)
                //System.out.print((char) i);
            sb.append((char)i);
            sb.append("\n\r");
            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
        return sb.toString();
    }
    
    public void copia(){
        Scrittore scrittore = new Scrittore(FileDaIncollare,this.leggi());
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Copiatore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

    @Override
    public void run(){
        copia();
    }
}
