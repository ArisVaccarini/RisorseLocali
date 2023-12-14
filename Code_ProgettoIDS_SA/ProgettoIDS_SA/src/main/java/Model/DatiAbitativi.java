package Model;

/**
 *  Questa classe rappresenta le informazioni abitative di un utente
 *  registrato in piattaforma.
 */
public class DatiAbitativi {

    private String comune;
    private String via;
    private String civico;
    private int CAP;

    public DatiAbitativi(String comune, String via, String civico, int CAP) {
        this.comune = comune;
        this.via = via;
        this.civico = civico;
        this.CAP = CAP;
    }

    public String getComune() {
        return comune;
    }

    public String getVia() {
        return via;
    }

    public String getCivico() {
        return civico;
    }

    public int getCAP() {
        return CAP;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public void setCAP(int CAP) {
        this.CAP = CAP;
    }

    @Override
    public String toString() {
        return "DatiAbitativi{" +
                "comune='" + comune + '\'' +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", CAP=" + CAP +
                '}';
    }
}
