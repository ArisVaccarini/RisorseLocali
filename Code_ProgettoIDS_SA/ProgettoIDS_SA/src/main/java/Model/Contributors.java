package Model;

import Command.InsertElementCommand;
import Command.ModifyElementCommand;
import Command.NewRequestCommand;
import Command.RequestCommand;
import Control.TypeChecker;
import Recources.StatusRequest;

/**
 * Questa classe ha il compito di rappresentare un contributore
 * della piattaforma. Il contributore è tale in quanto tutte le
 * operazioni di alterazione della conoscenza della base di dati,
 * sono sottoposte a verifiche da parte dei Curatori della piattaforma.
 */
public class Contributors implements Contribuzione {

    private String nomeUtente;
    private String email;
    private String password;
    private DatiAnagrafici anagrafica;
    private DatiAbitativi residenza;
    public Contributors(String nomeUtente, String email, String password,
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

    public RequestCommand addNewComponent(ElementiMappa newElem){
        InsertElementCommand command = new InsertElementCommand(StatusRequest.PENDING,
                this.getNomeUtente(),"Richiesta di inserimento -> " + newElem.getClass().getSimpleName(),
                newElem, new TypeChecker().checkTipo(newElem));
        return new NewRequestCommand(command, newElem) ;
    }

    @Override
    public RequestCommand modifyComponent(ElementiMappa newElem,String idElementoToModify) {
        ModifyElementCommand command = new ModifyElementCommand(StatusRequest.PENDING,
                this.getNomeUtente(), "Richiesta di modifica -> Modifica " +
                        newElem.getClass().getSimpleName(),newElem,idElementoToModify, new TypeChecker().checkTipo(newElem));
        return new NewRequestCommand(command, newElem,idElementoToModify);
    }
}
