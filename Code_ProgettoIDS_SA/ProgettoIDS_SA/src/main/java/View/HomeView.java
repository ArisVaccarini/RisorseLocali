package View;

import java.io.IOException;
import java.util.Scanner;

public class HomeView {

    public HomeView() {}

    public void mainMenu() throws IOException {
        while (true){
            System.out.println("-- SCEGLIERE L'OPERAZIONE --" + "\n" + "1 - CONTRIBUZIONE" + "\n" +
                    "2 - VISUALIZZAZIONE TERRITORIO\n" + "3 - ESCI");
            Scanner scanner = new Scanner(System.in);
            String op = scanner.nextLine();
            chooseOperation(op);
        }
    }

    public void chooseOperation(String op) throws IOException {
        switch (op){
            case "1":
                ContribuisciAutView contribuisciAutView = new ContribuisciAutView();
                contribuisciAutView.mainMenu();
                break;
            case "2":
                VisualizeContentView visualizeContentView = new VisualizeContentView();
                visualizeContentView.mainMenu();
                break;
            case "3":
                System.out.println("Uscita in corso...");
                System.exit(0);
        }
    }

}
