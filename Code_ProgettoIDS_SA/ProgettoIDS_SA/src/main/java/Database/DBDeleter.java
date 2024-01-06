package Database;

import Model.ElementiMappa;
import Model.POI;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBDeleter implements DBComponent {

    private DBMediator mediator;

    public DBDeleter() {
    }

    @Override
    public void setMediator(DBMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "DBDeleter";
    }


    public void deletePoiFromId(ElementiMappa elementMappa) {
        String query = "DELETE FROM modifica_pending_poi WHERE id_poi = ?";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1,elementMappa.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOldPath(String id, String table) {
        String query = "DELETE FROM " + table + " WHERE id_itinerari = ?";
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}