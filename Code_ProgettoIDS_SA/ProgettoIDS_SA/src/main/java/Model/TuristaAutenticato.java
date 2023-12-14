package Model;

import Recources.Ruoli;

public class TuristaAutenticato implements Account {

    private String nomeUtente;
    private String email;
    private String password;
    private DatiAnagrafici anagrafica;
    private DatiAbitativi residenza;
    public TuristaAutenticato(String nomeUtente, String email, String password,
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
    public TuristaAutenticato getAccount() {
        return this;
    }


    @Override
    public String toString() {
        return "TuristaAutenticato{" +
                "nomeUtente='" + nomeUtente + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", anagrafica=" + anagrafica.toString() +
                ", residenza=" + residenza.toString() +
                '}';
    }
}
