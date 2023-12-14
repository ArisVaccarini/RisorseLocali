package Model;

/**
 * Questa classe ha il compito di rappresentare un utente generico,
 * quindi un turista e le sue mansioni.
 */
public class Turista {
    Account nuovoAccount;

    public Turista() {}

    public void setNewAccount(Account nuovoAccount){
        this.nuovoAccount = nuovoAccount;
    }

    /**
     * Il turista conferma l'operazione di registrazione in piattaforma
     * restituendo l'account da registrare
     *
     * @return L'account da registrare
     */
    public Account confirmRegistrationNewAccount(){
        return this.nuovoAccount.getAccount();
    }

}
