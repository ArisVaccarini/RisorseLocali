package Model;

import Command.InsertElementCommand;
import Command.ModifyElementCommand;
import Command.NewRequestCommand;
import Command.RequestCommand;
import Control.TypeChecker;
import Recources.StatusRequest;

/**
 * Questa classe ha il compito di rappresentare un contributore
 * autorizzato della piattaforma. Il contributore è detto autorizzato
 * in quanto tutte le operazioni di alterazione della conoscenza della
 * base di dati, non sono sottoposte a verifiche da parte dei Curatori
 * della piattaforma.
 */
public class ContributorsAutorizzato implements Contribuzione {

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

    @Override
    public RequestCommand addNewComponent(ElementiMappa newElem){
        InsertElementCommand command = new InsertElementCommand(StatusRequest.APPROVED, this.getNomeUtente(),
                "Inserimento -> " + newElem.getClass().getSimpleName(), newElem, new TypeChecker().checkTipo(newElem));
        NewRequestCommand newCommand = new NewRequestCommand(command,newElem);
        newCommand.execute();
        return newCommand.getCommand();
    }

    @Override
    public RequestCommand modifyComponent(ElementiMappa newElem, String idElementoToModify) {
        ModifyElementCommand command = new ModifyElementCommand(StatusRequest.APPROVED,
                this.getNomeUtente(),"Modifica -> " + newElem.getClass().getSimpleName(),
                newElem,idElementoToModify, new TypeChecker().checkTipo(newElem));
        NewRequestCommand newCommand = new NewRequestCommand(command, newElem,idElementoToModify);
        newCommand.execute();
        return newCommand.getCommand();
    }
}
