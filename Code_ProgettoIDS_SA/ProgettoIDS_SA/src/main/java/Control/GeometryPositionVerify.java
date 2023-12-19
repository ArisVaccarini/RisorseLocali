package Control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

import Recources.Coordinate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Questa classe ha la responsabilità di determinare se un POI
 * è all'interno del comune oppure no.
 */
public class GeometryPositionVerify {

    /**
     * Verifica se un punto passatogli è al'interno del comune di riferimento
     *
     * @param latitudine del punto passatogli
     * @param longitudine del punto passatogli
     * @return   true se il punto è all'interno del comune, false altrimenti.
     * @throws IOException qualora non fosse possibile consultare la risposte HTTP
     */
    public boolean verifyPointInComune(double latitudine, double longitudine) throws IOException {
        String response = this.getHttpResponse();
        if(response == null)
            return false;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        ArrayList<ArrayList<Coordinate>> allCoordinate = this.extractAllPolygon(jsonNode);
        return isPointInsideComune(latitudine, longitudine, allCoordinate);
    }

    /**
     * Restituisce il risultato della query overpass inoltrata come
     * chiamata API ad OverPass Turbo.
     *
     * @return i risultati della query
     * @throws IOException qualora non fosse possibile consultare la risposte HTTP
     */
    private String getHttpResponse() throws IOException{
        String query = "[out:json];" +
                "rel[admin_level=\"8\"][name=\"Senigallia\"];" +
                "out geom;";
        String encodeQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        URL url = new URL("https://overpass-api.de/api/interpreter?data=" + encodeQuery);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        if(connection.getResponseCode() != 200)
            return null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
            response.append(line);
        return response.toString();
    }

    /**
     * Estrae tutti i poligoni che formano il comune di riferimento, dal file
     * JSON riportato dalla risposta della chiamata API verso OverPass Turbo
     *
     * @param jsonNode l'albero JSON della risposta HTTP
     * @return la lista di tutti i poligoni che formano il comune
     */
    private ArrayList<ArrayList<Coordinate>> extractAllPolygon(JsonNode jsonNode){
        ArrayList<ArrayList<Coordinate>> allCoordinate = new ArrayList<>();
        JsonNode elementsNode = jsonNode.get("elements");
        if (elementsNode != null && elementsNode.isArray()) {
            for (JsonNode elementNode : elementsNode) {
                String elementType = elementNode.get("type").asText();
                if ("relation".equals(elementType)) {
                    JsonNode membersNode = elementNode.get("members");
                    if (membersNode != null && membersNode.isArray()) {
                        for (JsonNode memberNode : membersNode) {
                            String memberType = memberNode.get("type").asText();
                            if ("way".equals(memberType)) {
                                JsonNode geomtryNode = memberNode.get("geometry");
                                ArrayList<Coordinate> coordinates = extractCoordinates(geomtryNode);
                                allCoordinate.add(coordinates);
                            }
                        }
                    }
                }
            }
        }
        return allCoordinate;
    }

    /**
     * Estrae le coordinate dei punti che compongono un poligono del
     * comune di riferimento
     *
     * @param geometryNode Il poligono, parte dell'area del comune di riferimento
     * @return La lista delle coordinate che compongono quel poligono
     */
    private ArrayList<Coordinate> extractCoordinates(JsonNode geometryNode) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        if (geometryNode != null && geometryNode.isArray()) {
            for (JsonNode coordinateNode : geometryNode) {
                Coordinate coordinate = new Coordinate(coordinateNode.get("lat").asDouble(),
                        coordinateNode.get("lon").asDouble());
                coordinates.add(coordinate);
            }
        }
        return coordinates;
    }

    /**
     * Verifica se il punto passatogli è all'interno del comune. Per ogni
     * poligono che forma il comune, viene chiamato il metodo isPointInsidePolygon
     * per verificare se il punto passato è presente in uno delle aree che copongono
     * l'intero comune
     *
     * @param latudine la latitudine del punto passatogli
     * @param longitudine la longitudine del punto passatogli
     * @param allCoordinates La lista di tutti i poligono e le loro rispettive coordinate
     * @return true se è all'interno del comune, false altrimenti
     */
    private boolean isPointInsideComune(double latudine, double longitudine, ArrayList<ArrayList<Coordinate>> allCoordinates) {
        for (ArrayList<Coordinate> comunePolygon : allCoordinates) {
            if (isPointInsidePolygon(latudine, longitudine, comunePolygon))
                return true;
        }
        return false;
    }

    /**
     *  Verifica se il punto passato è all'interno ad almeno un poligono
     *  che compone il comune.
     *
     * @param latidudine del punto passato
     * @param longitudine del punto passato
     * @param allCoordinates la lista di tutti i poligoni con tutti le coordinate che li compongono
     * @return true se il punto è all'interno di un poligono, false altrimenti
     */
    private boolean isPointInsidePolygon(double latidudine, double longitudine,  ArrayList<Coordinate> allCoordinates) {
        for (Coordinate coordinates : allCoordinates) {
            int intersectCount = 0;
            for (int i = 0; i < allCoordinates.size() - 1; i++) {
                double xi = allCoordinates.get(i).getLat();
                double yi = allCoordinates.get(i).getLon();
                double xj = allCoordinates.get(i + 1).getLat();
                double yj = allCoordinates.get(i + 1).getLon();
                if (((yi > longitudine) != (yj > longitudine)) &&
                        (latidudine < (xj - xi) * (longitudine - yi) / (yj - yi) + xi)) {
                    intersectCount++;
                }
            }
            if (intersectCount % 2 == 1)
                return true;
        }
        return false;
    }


}
