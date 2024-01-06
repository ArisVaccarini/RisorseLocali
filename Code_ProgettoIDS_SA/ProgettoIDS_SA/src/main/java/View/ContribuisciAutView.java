package View;

import Controller.ContributionController;
import Model.*;
import Recources.Coordinate;

import java.io.IOException;
import java.util.Scanner;

public class ContribuisciAutView {

    private ContributionController contributionController;

    public ContribuisciAutView() {
        this.contributionController = new ContributionController(new Contributors("sa","sa",
                "sa",new DatiAnagrafici(null,null,null,null),new DatiAbitativi(null,null,
        null,0)));
    }

    public void mainMenu()  {
        boolean flag = true;
        while (flag){
            System.out.println("-- CONTRIBUISCI --" + "\n" + "1 - AGGIUNGI POI" + "\n" +
                    "2 - ESCI");
            Scanner scanner = new Scanner(System.in);
            String op = scanner.nextLine();
            switch (op){
                case "1":
                    try {
                        this.insertNewPOI();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2":
                    flag = false;
                    break;
                default:
                    System.out.println("Operazione non valida...");
            }
        }
    }

    public void insertNewPOI() throws IOException {
        boolean flag = false;
        while (!flag) {
            System.out.println("Contribuzione" + "\n" + "Inserire nome del POI");
            Scanner scanner = new Scanner(System.in);
            String nome = scanner.nextLine();
            System.out.println("\n" + "Inserire descrizione");
            String descrizione = scanner.nextLine();
            System.out.println("\n" + "Inserire latitudine");
            double lat = Double.parseDouble(scanner.nextLine());
            System.out.println("\n" + "Inserire longitudine");
            double lon = Double.parseDouble(scanner.nextLine());
            POI newPoi = new POI(nome,descrizione,new Coordinate(lat,lon));
            flag = this.consultSystemToContinue(newPoi);
        }
    }

    private boolean consultSystemToContinue(POI newPoi) throws IOException {
        if (!(this.contributionController.isPointInComuneVerify(newPoi))){
            System.out.println("Il punto inserito non fa parte del comune");
            return false;
        }
        else if(this.contributionController.alreadyInMap(newPoi)){
            System.out.println("Punto già esistente");
            return false;
        }
        return confirmRequest(newPoi);
    }

    public boolean confirmRequest(POI newPoi){
        System.out.println("Confermare la contribuzione? (Si o No)");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("Si")){
            this.contributionController.insertElementInMap(newPoi);
            System.out.println("Contribuzione portata a termine con successo!");
            return true;
        }
        System.out.println("Ritorno all'operazione precedente...");
        return false;
    }




}
