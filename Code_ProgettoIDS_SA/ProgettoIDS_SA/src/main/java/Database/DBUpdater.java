package Database;

import Model.Itinerari;
import Model.POI;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUpdater implements DBComponent{

    private DBMediator mediator;

    public DBUpdater() {
    }

    @Override
    public void setMediator(DBMediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public String getName() {
        return "DBUpdater";
    }

    public void updateStatus(String idElement, String status,String typeElement){
        String query = "UPDATE " + typeElement + " SET stato = ? WHERE id = ?";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, status);
            statement.setString(2, idElement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoi(POI poiNuovo,String idElementToModify){
        String query = "UPDATE poi SET nome = ?, descrizione = ?, latitudine = ?, longitudine = ? WHERE id = ?";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, poiNuovo.getNome());
            statement.setString(2, poiNuovo.getDescrizione());
            statement.setDouble(3, poiNuovo.getPosizione().getLat());
            statement.setDouble(4, poiNuovo.getPosizione().getLon());
            statement.setString(5, idElementToModify);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItin(Itinerari itinNuovo, String idElementToModify){
        String query = "UPDATE itinerari SET nome = ?, descrizione = ? WHERE id = ?";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, itinNuovo.getNome());
            statement.setString(2, itinNuovo.getDescrizione());
            statement.setString(3, idElementToModify);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}