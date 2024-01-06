package Database;

import Model.ElementiMappa;
import Model.Itinerari;
import Model.MappaComune;
import Model.POI;
import Recources.Coordinate;
import Recources.StatusElement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBSelector implements DBComponent{

    private DBMediator mediator;

    public DBSelector() {
    }

    @Override
    public void setMediator(DBMediator mediator) {
        this.mediator = mediator;
    }
    @Override
    public String getName() {
        return "DBSelector";
    }

    public HashMap<String, ElementiMappa> selectAllPoint() {
        HashMap<String, ElementiMappa> mappaComune = new HashMap<>();
            try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement("SELECT * FROM poi")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    POI poi = new POI(resultSet.getString("id"),resultSet.getString("nome"),resultSet.getString("descrizione"),
                            new Coordinate(resultSet.getDouble("latitudine"),resultSet.getDouble("longitudine")),
                            StatusElement.convertStatus(resultSet.getString("stato")));
                    mappaComune.put(poi.getId(),poi);
                }
            }
        } catch (
        SQLException e) {
            e.printStackTrace();
        }
            return mappaComune;
    }


    public HashMap<String, ElementiMappa> selectAllItinerari() {
        HashMap<String, ElementiMappa> mappaComune = new HashMap<>();
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement("SELECT * FROM itinerari")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Itinerari itinerari = new Itinerari(resultSet.getString("id"), resultSet.getString("nome"),
                           resultSet.getString("descrizione"),selectPoiOfItinerari(resultSet.getString("id")), StatusElement.convertStatus(resultSet.getString("stato")));
                    mappaComune.put(itinerari.getId(), itinerari);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return mappaComune;
    }


    private ArrayList<POI> selectPoiOfItinerari(String id) {
        ArrayList<POI> listPoi = new ArrayList<>();
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement("SELECT poi.id, poi.nome, poi.descrizione, poi.stato, poi.latitudine, poi.longitudine, poi.stato FROM poi " +
                "JOIN itinerari_poi ON poi.id = itinerari_poi.id_poi WHERE itinerari_poi.id_itinerari = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    listPoi.add(new POI(resultSet.getString("id"),resultSet.getString("nome"),resultSet.getString("descrizione"),
                            new Coordinate(resultSet.getDouble("latitudine"),resultSet.getDouble("longitudine")),
                            StatusElement.convertStatus(resultSet.getString("stato"))));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return listPoi;
    }


    public ElementiMappa selectByID(String elementID) {
        POI poi = null;
        try (
                PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement("SELECT * FROM poi where id = " + elementID)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    poi = new POI(resultSet.getString("id"),resultSet.getString("nome"),resultSet.getString("descrizione"),
                            new Coordinate(resultSet.getDouble("latitudine"),resultSet.getDouble("longitudine")),
                            StatusElement.convertStatus(resultSet.getString("stato")));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return poi;
    }

    public String selectTableOfID(String elementID) {
        String tipo = "";
         try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement(" SELECT itinerari AS nome_tipo FROM itinerari WHERE id = " + elementID +
                 " UNION ALL SELECT poi AS nome_tipo FROM poi WHERE id = " + elementID)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                    tipo = resultSet.getString("nome_tipo");
                    System.out.println(tipo);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return tipo;
    }

    public String selectLastId(String tipo) {
        String maxId = null;
        try (PreparedStatement statement = DBManager.getInstance().getConnection().prepareStatement("SELECT id FROM " + tipo + " ORDER BY CAST(SUBSTRING(id FROM 2) AS INTEGER) DESC LIMIT 1")){
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    maxId = resultSet.getString("id");
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }




}
