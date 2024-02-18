package com.unicam.risorseLocali.Control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicam.risorseLocali.Core.Model.Entity.Coordinate;
import com.unicam.risorseLocali.Core.Model.Entity.POI;
import com.unicam.risorseLocali.Exception.GeometryPositionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe ha il compito di verificare le
 * coordinate associate un POI controllando se
 * appartiene o meno al comune.
 */

public class GeometryPositionVerify {

    public boolean verifyPointInComune(double latitudine, double longitudine){
        try {
            String response = this.getHttpResponse();
            if(response == null)
                return false;
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            ArrayList<ArrayList<Coordinate>> allCoordinate = this.extractAllPolygon(jsonNode);
            return isPointInsideComune(latitudine, longitudine, allCoordinate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyIfCoordinateExist(List<POI> pois, Coordinate coordinate){
        pois.removeIf(element -> !(element.getPosizione().equals(coordinate)));
        if(pois.isEmpty())
            return true;
        throw new GeometryPositionException("Coordinate associate a un " +
                "POI esistente oppure un a POI in approvazione." +
                "Definirne di nuove.");
    }


    private String getHttpResponse() throws IOException {
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

    private boolean isPointInsideComune(double latudine, double longitudine, ArrayList<ArrayList<Coordinate>> allCoordinates) {
        for (ArrayList<Coordinate> comunePolygon : allCoordinates) {
            if (isPointInsidePolygon(latudine, longitudine, comunePolygon))
                return true;
        }
        throw new GeometryPositionException(latudine,longitudine);
    }

       private boolean isPointInsidePolygon(double latidudine, double longitudine,  ArrayList<Coordinate> allCoordinates) {
        for (Coordinate coordinates : allCoordinates) {
            int intersectCount = 0;
            for (int i = 0; i < allCoordinates.size() - 1; i++) {
                double xi = allCoordinates.get(i).getLatitudine();
                double yi = allCoordinates.get(i).getLongitudine();
                double xj = allCoordinates.get(i + 1).getLatitudine();
                double yj = allCoordinates.get(i + 1).getLongitudine();
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
