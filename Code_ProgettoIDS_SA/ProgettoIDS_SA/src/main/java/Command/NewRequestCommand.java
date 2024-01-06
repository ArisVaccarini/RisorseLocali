package Command;

import Database.DBManager;
import Model.ElementiMappa;
import Recources.CommandType;

/**
 * Questa classe è responsabile del salvataggio dei comandi
 * in stato "Pending" per la loro futura esecuzione.
 */
public class NewRequestCommand implements RequestCommand {

    private final BaseRequestCommand command;
    private final ElementiMappa idNewElement;
    private String idElementToModify;

    public NewRequestCommand(BaseRequestCommand command, ElementiMappa idNewElement) {
        this.command = command;
        this.idNewElement = idNewElement;
    }

    public NewRequestCommand(BaseRequestCommand command, ElementiMappa idNewElement, String idElementToModify) {
        this.command = command;
        this.idNewElement = idNewElement;
        this.idElementToModify = idElementToModify;
    }

    public BaseRequestCommand getCommand() {
        return command;
    }

    public String getIdElementToModify() {
        if(this.idElementToModify == null)
            return null;
        return idElementToModify;
    }

    @Override
    public CommandType getType() {
        return command.getType();
    }

    @Override
    public String showHeaderCommand() {
        return "System save command";
    }

    @Override
    public String showBodyCommand() {
        return this.getCommand().showHeaderCommand()
                + " " + this.showBodyCommand();
    }

    @Override
    public boolean execute() {
        if(this.command == null || this.idNewElement == null)
            return false;
        DBManager.getInstance().reactOnInsertNewRequest(this.getCommand(),
                this.idNewElement ,this.getIdElementToModify());
        return true;
    }
}
