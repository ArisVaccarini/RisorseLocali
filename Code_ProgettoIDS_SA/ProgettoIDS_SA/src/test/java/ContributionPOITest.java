import Controller.ContributionController;
import Model.*;
import Recources.Coordinate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ContributionPOITest {

    ContributionController contributionController;

    @Test
    void testInsertAuthurizedPOI() throws IOException {
        this.contributionController = new ContributionController(new ContributorsAutorizzato("Sam","sa",
                "sa",new DatiAnagrafici(null,null,null,null),new DatiAbitativi(null,null,
                null,0)));
        POI correctPOi = new POI("Marzocca","Capitale della droga",
                new Coordinate(43.7164785, 13.2197727));
        assertTrue(this.contributionController.isPointInComuneVerify(correctPOi));
        assertFalse(this.contributionController.alreadyInMap(correctPOi));
        assertTrue(this.contributionController.insertElementInMap(correctPOi));
    }

    @Test
    void testInsertPendingPOI() throws IOException {
        this.contributionController = new ContributionController(new Contributors("Sam","samupira@prova.it",
                "admin",new DatiAnagrafici("Samuele","Pirani","Prova","+39P"),new DatiAbitativi("Senigallia","via p",
                "x",60019)));
        POI correctPOi = new POI("Matis","Ristorante del foro",
                new Coordinate(43.7159720, 13.2196463));
        assertTrue(this.contributionController.isPointInComuneVerify(correctPOi));
        assertFalse(this.contributionController.alreadyInMap(correctPOi));
        assertTrue(this.contributionController.insertElementInMap(correctPOi));
    }

    @Disabled
    void testModifyPendingPOI() throws IOException {
        this.contributionController = new ContributionController(new ContributorsAutorizzato("Sam","samupira@prova.it","admin",new DatiAnagrafici("Samuele","Pirani","Prova","+39P"),new DatiAbitativi("Senigallia","via p",
                "x",60019)));
        POI selectPOi = new POI(new MappaComune().obtainSpecificElement("P2"));
        POI modifyPOI = new POI(selectPOi.getNome(), selectPOi.getDescrizione(),selectPOi.getPosizione());
        modifyPOI.setId("P3");
        modifyPOI.setDescrizione("Cittadina vuota");
        modifyPOI.setNome("Montignano");
        modifyPOI.setPosizione(new Coordinate(43.6728849, 13.2913320));
        assertTrue(this.contributionController.isPointInComuneVerify(modifyPOI));
        assertTrue(this.contributionController.modifyElementInMap(modifyPOI,selectPOi.getId()));
    }
}
