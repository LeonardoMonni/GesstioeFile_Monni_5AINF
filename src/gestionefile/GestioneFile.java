package gestionefile;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Monni Leonardo
 * @version 26/01/23
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1)LETTURA del file json
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        
        //2)ELABORAZIONE
        Scanner scanner = new Scanner (System.in);
        System.out.println("Scegliere l'username: "); //chiedi all'utente di scegliere username 
        String username = scanner.nextLine();
        
        System.out.println("Scegliere la password: "); //chiedi all'utente di scegliere password 
        String password = scanner.nextLine();
        
    
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv", username + ";" + password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //4) COPIATURA 
        Copiatore copiatore = new Copiatore("output.csv","copia.csv");
        copiatore.start();
        try {
            copiatore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
