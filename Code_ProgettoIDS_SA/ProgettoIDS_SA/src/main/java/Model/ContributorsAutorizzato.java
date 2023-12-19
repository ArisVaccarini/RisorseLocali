package Model;

/**
 * Questa classe ha il compito di rappresentare un contributore
 * autorizzato della piattaforma. Il contributore è detto autorizzato
 * in quanto tutte le operazioni di alterazione della conoscenza della
 * base di dati, non sono sottoposte a verifiche da parte dei Curatori
 * della piattaforma.
 */
public class ContributorsAutorizzato implements Account{

    private String nomeUtente;
    private String email;
    private String password;
    private DatiAnagrafici anagrafica;
    private DatiAbitativi residenza;
    public ContributorsAutorizzato(String nomeUtente, String email, String password,
                              DatiAnagrafici anagrafica, DatiAbitativi residenza) {
        this.nomeUtente = nomeUtente;
        this.email = email;
        this.password = password;
        this.anagrafica = anagrafica;
        this.residenza = residenza;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public DatiAnagrafici getAnagrafica() {
        return anagrafica;
    }

    public DatiAbitativi getResidenza() {
        return residenza;
    }
    @Override
    public void modifyAccount() {

    }

    @Override
    public Account getAccount() {
        return this;
    }
}
