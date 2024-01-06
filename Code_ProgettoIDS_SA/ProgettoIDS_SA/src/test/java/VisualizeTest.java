import Controller.VisualizeController;
import Model.ElementiMappa;
import Model.Itinerari;
import Model.POI;
import Recources.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class VisualizeTest {

    VisualizeController visualizeController;

    @Test
    void visualizeListPOI(){
        this.visualizeController = new VisualizeController();
        POI selectPOI = null;
        ArrayList<? extends ElementiMappa> listaPOI = this.visualizeController.filter("1");
        for (ElementiMappa elementiMappa: listaPOI) {
            if(elementiMappa.getId().equals("P1"))
                selectPOI = new POI(elementiMappa);
            assertNotEquals(elementiMappa.getId(),"P4");
        }
        assertEquals(selectPOI.getNome(),"Marzocca");
        assertEquals(selectPOI.getPosizione(), new Coordinate(43.7164785,13.2197727));
    }

    @Test
    void visualizeListItinerari(){
        this.visualizeController = new VisualizeController();
        Itinerari selectItin = null;
        ArrayList<? extends ElementiMappa> listaItin = this.visualizeController.filter("2");
        for (ElementiMappa elementiMappa: listaItin) {
            if(elementiMappa.getId().equals("I2"))
                selectItin = new Itinerari(elementiMappa);
            assertNotEquals(elementiMappa.getId(),"P4");
        }
        assertEquals(selectItin.getNome(),"P-11");
    }

}
