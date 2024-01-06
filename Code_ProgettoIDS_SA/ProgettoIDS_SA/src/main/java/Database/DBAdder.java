package Database;

import Command.BaseRequestCommand;
import Model.ElementiMappa;
import Model.Itinerari;
import Model.POI;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBAdder implements DBComponent{

    private DBMediator mediator;
    public DBAdder() throws SQLException {
    }

    @Override
    public void setMediator(DBMediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public String getName() {
        return "DBAdder";
    }

    public void addNewPoi(POI elementiMappa){
        String query1 = "INSERT INTO poi (id, nome, descrizione, stato ,latitudine, longitudine) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query1)) {
            statement.setString(1, elementiMappa.getId());
            statement.setString(2, elementiMappa.getNome());
            statement.setString(3, elementiMappa.getDescrizione());
            statement.setString(4, elementiMappa.getStatus().name());
            statement.setDouble(5, elementiMappa.getPosizione().getLat());
            statement.setDouble(6, elementiMappa.getPosizione().getLon());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequestPOI(BaseRequestCommand elementiMappa, String idElem){
        String query = "INSERT INTO richieste_poi (id, stato, id_account, id_nuovo,descrizione,tipo) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, elementiMappa.getIdReq());
            statement.setString(2, elementiMappa.getStatus().name());
            statement.setString(3, elementiMappa.getUsernameAuthor());
            statement.setString(4, idElem);
            statement.setString(5, elementiMappa.getDescription());
            statement.setString(6, elementiMappa.getType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequestItinerario(BaseRequestCommand elementiMappa, String idElem){
        String query = "INSERT INTO richieste_itinerari (id, stato, id_account, id_nuovo,descrizione,tipo) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, elementiMappa.getIdReq());
            statement.setString(2, elementiMappa.getStatus().name());
            statement.setString(3, elementiMappa.getUsernameAuthor());
            statement.setString(4, idElem);
            statement.setString(5, elementiMappa.getDescription());
            statement.setString(6, elementiMappa.getType().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewItinerari(Itinerari elementiMappa){
        String query1 = "INSERT INTO itinerari (id, nome, descrizione, stato) VALUES(?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query1)) {
            statement.setString(1, elementiMappa.getId());
            statement.setString(2, elementiMappa.getNome());
            statement.setString(3, elementiMappa.getDescrizione());
            statement.setString(4, elementiMappa.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.associatePOIToItinerary(elementiMappa.getPercorso(),elementiMappa.getId(),"itinerari_poi");
    }
    
    private void associatePOIToItinerary(List<POI> percorso,String idItin, String nameTab){
        for (POI poi : percorso) {
            String query = "INSERT INTO " + nameTab + " (id_itinerari, id_poi) VALUES (?,?)";
            try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)){
                statement.setString(1, idItin);
                statement.setString(2, poi.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void addNewModifyPOIRequest(POI elementiMappa, String idRef){
        String query = "INSERT INTO modifica_pending_poi (id_poi, nome, descrizione, latitudine, longitudine, id_ref) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, elementiMappa.getId());
            statement.setString(2, elementiMappa.getNome());
            statement.setString(3, elementiMappa.getDescrizione());
            statement.setDouble(4, elementiMappa.getPosizione().getLat());
            statement.setDouble(5, elementiMappa.getPosizione().getLon());
            statement.setString(6, idRef);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewModifyItinRequest(Itinerari elementiMappa, String idRef){
        String query = "INSERT INTO modifica_pending_itinerari (id_itinerari, nome, descrizione, id_ref) VALUES(?,?,?,?)";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, elementiMappa.getId());
            statement.setString(2, elementiMappa.getNome());
            statement.setString(3, elementiMappa.getDescrizione());
            statement.setString(4, idRef);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.associatePOIToItinerary(elementiMappa.getPercorso(),elementiMappa.getId(),"percorso_provvisorio");
    }

    public void addNewPath(Itinerari elementiMappa, String idElementToModify){
        for (POI poi : elementiMappa.getPercorso()) {
            String query = "INSERT INTO itinerari_poi (id_itinerari, id_poi) VALUES(?,?)";
            try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
                statement.setString(1, idElementToModify);
                statement.setString(2, poi.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
