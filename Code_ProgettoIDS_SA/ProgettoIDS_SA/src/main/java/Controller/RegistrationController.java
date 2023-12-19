package Controller;

import Model.Account;
import Model.Turista;
import Model.TuristaAutenticato;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Questa classe ha la responsabilita di gestire le operazioni scaturite
 * dalla registrazione di un utente in piattaforma.
 */
public class RegistrationController {

    private Turista turista;

    public RegistrationController() {
        this.turista = new Turista();
    }

    /**
     * Setta nel turista l'account precedentemente creato
     * @param newAccount il nuovo account
     */
    public void newAccount(Account newAccount){
        this.turista.setNewAccount(newAccount);
    }

    public boolean registerNewAccount(boolean confirm) throws IOException {
        if(confirm){
            FileWriter writer = new FileWriter("provaScrittura.txt");
            writer.write(turista.confirmRegistrationNewAccount().getAccount().toString());
            writer.close();
            this.resetRegistration();
            return true;
        }
        this.resetRegistration();
        return false;
    }

    /**
     * Resetta lo stato della registrazione
     */
    public void resetRegistration(){
        this.turista = null;
    }
}
