package Model;

/**
 *  Questa classe rappresenta le informazioni anagrafiche di un utente
 *  registrato in piattaforma.
 */
public class DatiAnagrafici {
    private String nome;
    private String cognome;
    private String cf;
    private String numTel;

    public DatiAnagrafici(String nome, String cognome, String cf, String numTel) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.numTel = numTel;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCf() {
        return cf;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "DatiAnagrafici{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cf='" + cf + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
