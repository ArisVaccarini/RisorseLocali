import Controller.ContributionController;
import Model.*;
import Recources.Coordinate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class ContributionItinerarioTest {

    private ContributionController contributionController;

    @Disabled
    void testInsertAuthurizedItinerario(){
        this.contributionController = new ContributionController(new ContributorsAutorizzato("Sam","sa",
                "sa",new DatiAnagrafici(null,null,null,null),new DatiAbitativi(null,null,
                null,0)));
        POI correctPOi = new POI("Marzocca","Capitale della droga",
                new Coordinate(43.7164785, 13.2197727));
        ArrayList<POI> list = new ArrayList<>();
        list.add(correctPOi);
        Itinerari correctItin = new Itinerari("P-11","Percorso - 11", list);
        //assertFalse(this.contributionController.alreadyInMap(correctItin));
        assertTrue(this.contributionController.insertElementInMap(correctItin));
    }

    @Disabled
    void testInsertPendingItinerario(){
        this.contributionController = new ContributionController(new Contributors("Sam","sa",
                "sa",new DatiAnagrafici(null,null,null,null),new DatiAbitativi(null,null,
                null,0)));
        POI correctPOi = new POI("Marzocca","Capitale della droga",
                new Coordinate(43.7164785, 13.2197727));
        ArrayList<POI> list = new ArrayList<>();
        list.add(correctPOi);
        Itinerari correctItin = new Itinerari("Percorso parmalat","Il latte del corso", list);
        assertFalse(this.contributionController.alreadyInMap(correctItin));
        assertTrue(this.contributionController.insertElementInMap(correctItin));
    }

    @Test
    void testModifyAuthorazedItinerario(){
        this.contributionController = new ContributionController(new Contributors("Sam","sa",
                "sa",new DatiAnagrafici(null,null,null,null),new DatiAbitativi(null,null,
                null,0)));
        POI correctPOi = new POI("Marzocca","Capitale della droga",
                new Coordinate(43.7164785, 13.2197727));
        ArrayList<POI> list = new ArrayList<>();
        list.add(correctPOi);
        Itinerari selectItin = new Itinerari(new MappaComune().getElementiMappa().get("I2"));
        Itinerari modifyItin = new Itinerari("Percorso - 12","Percorso figo", list);
        assertTrue(this.contributionController.modifyElementInMap(modifyItin,selectItin.getId()));
    }

}
