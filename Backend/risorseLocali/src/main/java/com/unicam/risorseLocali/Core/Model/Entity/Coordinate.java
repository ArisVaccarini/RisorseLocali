package com.unicam.risorseLocali.Core.Model.Entity;

import jakarta.persistence.Embeddable;

import java.util.Objects;

    /**
     * Questa classe ha la responsabilità di tener traccia della posizione
     * di un POI all'interno del comune di riferimento.
     */
    @Embeddable
    public class Coordinate {

        private double latitudine;
        private double longitudine;

        public Coordinate() {
        }

        public Coordinate(double lat, double lon) {
            this.latitudine = lat;
            this.longitudine = lon;
        }

        public double getLatitudine() {
            return this.latitudine;
        }

        public double getLongitudine() {
            return this.longitudine;
        }

        public void setLatitudine(double lat) {
            this.latitudine = lat;
        }

        public void setLongitudine(double lon) {
            this.longitudine = lon;
        }

        @Override
        public String toString() {
            return "Posizione ->" +
                    "lat=" + this.latitudine +
                    ", lon=" + this.longitudine;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return Double.compare(that.latitudine, latitudine) == 0 && Double.compare(that.longitudine, longitudine) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(latitudine, longitudine);
        }
    }
