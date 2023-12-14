package View;

import Controller.RegistrationController;
import Model.*;
import Recources.Ruoli;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        RegistrationController registrationController = new RegistrationController();
        System.out.println("Credenziali" + "\n" + "Inserire nomeUtente");
        Scanner scanner = new Scanner(System.in);
        String nomeUtente = scanner.nextLine();
        System.out.println("\n" + "Inserire email");
        String email = scanner.nextLine();
        System.out.println("\n" + "Inserire password");
        String password = scanner.nextLine();
        System.out.println("\n" + "Inserire ruolo");
        String ruolo = scanner.nextLine();
        ruolo = ruolo.toUpperCase();
        DatiAnagrafici anagrafica = scriviAnagrafica();
        DatiAbitativi abitativi = scriviResidenza();
        if(!(ruolo.equals(Ruoli.TURISTA_AUTENTICATO.toString())))
             throw new IllegalArgumentException("Ruolo inesistente");
        registrationController.newAccount(new TuristaAutenticato(nomeUtente,email,password,anagrafica,abitativi));
        System.out.println("\n" + "Vuoi confermare la registrazione? (Si o No)");
        String confirm = scanner.nextLine();
        if(confirm.equals("Si")) {
            registrationController.registerNewAccount(true);
            System.out.println("Registrazione effettuata");
        }
        else {
            registrationController.registerNewAccount(false);
            System.out.println("Registrazione annullata");
        }
    }

    public static DatiAnagrafici scriviAnagrafica(){
        System.out.println("Dati anagrafici" + "\n" + "Inserire nome");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("\n" + "Inserire cognome");
        String cognome = scanner.nextLine();
        System.out.println("\n" + "Inserire cf");
        String cf = scanner.nextLine();
        System.out.println("\n" + "Inserire numeroTelefonico");
        String numTel = scanner.nextLine();
        return new DatiAnagrafici(nome,cognome,cf,numTel);
    }

    public static DatiAbitativi scriviResidenza(){
        System.out.println("Dati abitativi" + "\n" + "Inserire comune");
        Scanner scanner = new Scanner(System.in);
        String comune = scanner.nextLine();
        System.out.println("\n" + "Inserire via");
        String via = scanner.nextLine();
        System.out.println("\n" + "Inserire civico");
        String civico = scanner.nextLine();
        System.out.println("\n" + "Inserire CAP");
        int cap = Integer.parseInt(scanner.nextLine());
        return new DatiAbitativi(comune,via,civico,cap);
    }

}
