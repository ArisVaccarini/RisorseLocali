package Recources;

import java.util.Objects;

/**
 * Questa classe ha la responsabilità di tener traccia della posizione
 * di un POI all'interno della mappa del comune di riferimento.
 */

public class Coordinate {

    private final double lat;
    private final double lon;

    public Coordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Posizione ->" +
                "lat=" + lat +
                ", lon=" + lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that.lat, lat) == 0 && Double.compare(that.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
