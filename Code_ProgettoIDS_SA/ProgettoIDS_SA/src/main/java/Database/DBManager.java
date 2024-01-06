package Database;

import Command.BaseRequestCommand;
import Model.ElementiMappa;
import Model.Itinerari;
import Model.POI;
import java.sql.*;
import java.util.HashMap;


/**
 * Questa classe ha il compito di fare da intermediario per le classi
 * che altrimenti non potrebbero comunicare
 */
public class DBManager implements DBMediator {

    private static DBManager instance;
    private HashMap<String, BaseRequestCommand> richieste;
    private final String jdbcUrl = "jdbc:postgresql://nutty-custard-apple.db.elephantsql.com:5432/qzyvtsah";
    private final String username = "qzyvtsah";
    private final String password = "XDu2m6N1NOfFgz635OHjuzidyGf4o5Rr";
    private Connection connection;
    DBAdder dbAdder = new DBAdder();
    DBDeleter dbDeleter = new DBDeleter();
    DBUpdater dbUpdater = new DBUpdater();
    DBSelector dbSelector = new DBSelector();


    public DBManager() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        }
        catch (SQLException e){}
    }

    public static DBManager getInstance(){
        if(instance == null) {
            try {
                instance = new DBManager();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public HashMap<String, ElementiMappa> selectAllPOI() {
        return this.dbSelector.selectAllPoint();
    }

    @Override
    public HashMap<String, ElementiMappa> selectAllItinerari() {
        return this.dbSelector.selectAllItinerari();
    }

    @Override
    public void reactOnInsertElement(ElementiMappa elementMappa) {
        if (elementMappa instanceof POI)
            this.dbAdder.addNewPoi(new POI(elementMappa));
        else
            this.dbAdder.addNewItinerari(new Itinerari(elementMappa));
    }

    @Override
    public void reactOnInsertOverrideElement(ElementiMappa elementiMappa, String idElemenToModify){
        switch (elementiMappa.getClass().getSimpleName()){
            case "POI":
                this.dbAdder.addNewModifyPOIRequest(new POI(elementiMappa),idElemenToModify);
                break;
            case "Itinerari":
                this.dbAdder.addNewModifyItinRequest(new Itinerari(elementiMappa),idElemenToModify);
                break;
        }
    }

    @Override
    public void reactOnUpdateStatus(ElementiMappa idElement, String status) {
        if(idElement instanceof POI)
            this.dbUpdater.updateStatus(idElement.getId(), status, "poi");
        else
            this.dbUpdater.updateStatus(idElement.getId(), status, "itinerari");
    }

    @Override
    public void reactOnInsertNewRequest(BaseRequestCommand richiesta, ElementiMappa idNewElem, String idElemToModify) {
        switch (richiesta.getType()){
            case INSERT_ELEMENT_COMMAND :
                if(idNewElem instanceof POI)
                    this.dbAdder.addRequestPOI(richiesta, idNewElem.getId());
                else
                    this.dbAdder.addRequestItinerario(richiesta, idNewElem.getId());
                break;
            case MODIFY_ELEMENT_COMMAND:
                if(idNewElem instanceof POI)
                    this.dbAdder.addRequestPOI(richiesta, idElemToModify);
                else
                    this.dbAdder.addRequestItinerario(richiesta, idElemToModify);
                break;
        }
    }

    @Override
    public void reactOnUpdateElement(ElementiMappa elementoNuovo, String idElementoVecchio) {
        switch(elementoNuovo.getClass().getSimpleName()){
            case "POI":
                this.dbUpdater.updatePoi(new POI(elementoNuovo), idElementoVecchio);
                break;
            case "Itinerari":
                this.dbUpdater.updateItin(new Itinerari(elementoNuovo), idElementoVecchio);
                this.dbDeleter.deleteOldPath(idElementoVecchio, "itinerari_poi");
                this.dbAdder.addNewPath(new Itinerari(elementoNuovo), idElementoVecchio);
                break;
        }
    }

    /**
     * Si occupa della generazione automatica degli id per quanto riguarda
     * i poi, gli itinerari e sia richieste riguardanti i poi e sia quelle rigurdanti gli itinerari.
     *
     * @param tipo      Il tipo degli id (poi, itinerari, richieste).
     *
     * @return una stringa contenente il nuovo ID.
     */
    public String incrementAndSelectLastId(String tipo) {
        String lastId = this.dbSelector.selectLastId(tipo);
        if (lastId != null) {
            try {
                String prefix = lastId.substring(0, 1);
                int numericPart = Integer.parseInt(lastId.substring(1));
                numericPart++;
                return prefix + numericPart;
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        else{
            switch (tipo){
                case "poi":
                    return "P1";
                case "itinerari":
                    return "I1";
                default :
                    return "R1";
            }
        }
        return null;
    }

    @Override
    public void reactOnDeleteElement(ElementiMappa elemento) {
        switch (elemento.getClass().getSimpleName()) {
            case "POI":
                this.dbDeleter.deletePoiFromId(elemento);
                break;
            case "Itinerari":
                this.dbDeleter.deleteOldPath(elemento.getId(),"modifica_pending_itinerari");
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
