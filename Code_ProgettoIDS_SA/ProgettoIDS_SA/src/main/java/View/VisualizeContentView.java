package View;

import Controller.VisualizeController;
import Model.ElementiMappa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VisualizeContentView {

    private VisualizeController visualizeController;
    public VisualizeContentView() {
        this.visualizeController = new VisualizeController();
    }

    public void mainMenu() throws IOException {
        boolean flag = true;
        while (flag){
            System.out.println("-- FILTRO CONTENUTI --" + "\n" + "1 - FILTRA PER POI" + "\n" +
                    "2 - FILTRA PER ITINERARIO\n" + "3 - ESCI");
            Scanner scanner = new Scanner(System.in);
            String op = scanner.nextLine();
            if(op.equals("3"))
                flag = false;
            else
                chooseOperation(op);
        }
    }

    public void chooseOperation(String op) throws IOException {
        switch (op){
            case "1":
                this.showContentFiltered(this.visualizeController.filter(op));
                break;
            case "2":
                break;
            default:
                System.out.println("Operazione non ammessa");
        }
    }

    public void showContentFiltered(ArrayList<? extends ElementiMappa> arrayList){
        boolean flag = true;
        while (flag){
         for (ElementiMappa content: arrayList) {
            System.out.println(content.showGeneralInformationComponent());
         }
            System.out.println("Inserire il nome uno dei contenuti da visualizzare, oppure premi 1 per uscire:");
            Scanner scanner = new Scanner(System.in);
            String op = scanner.nextLine();
            if(op.equals("1"))
                flag = false;
            else
                System.out.println(this.visualizeController.getMappaComune()
                    .obtainSpecificPOI(op).showSpecificInformationComponent());
        }
    }

}
