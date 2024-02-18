package com.unicam.risorseLocali.Exception;

public class GeometryPositionException extends RuntimeException{
    public GeometryPositionException(String message) {
        super(message);
    }

    public GeometryPositionException(double lat, double lon) {
        super(String.format("Coordinate: {[Latitudine -> %f],[Longitudine -> %f]}, fuori dal" +
                " comune di riferimento",lat , lon));
    }

}
