package lukuvinkkikirjasto.ui;

import java.util.Scanner;
import java.util.List;

import lukuvinkkikirjasto.domain.*;

/**
 * Text User Inferface
 */
public class ConsoleUi {
    private final Scanner scanner;
    private final TipService tipService;

    public ConsoleUi(Scanner scanner, TipService tipService) {
        this.scanner = scanner;
        this.tipService = tipService;
    }

    public void start() {
        while (true) {
            System.out.println("Komennot:");
            System.out.println("add - lisää vinkki");
            System.out.println("list - listaa vinkit");
            System.out.println("quit - lopettaa");
            String cmd = scanner.nextLine();
            switch (cmd) {
            case "add":
                System.out.println("Otsikko?");
                String title = scanner.nextLine();
                System.out.println("Url?");
                String url = scanner.nextLine();

                tipService.createTip(title, url);

                System.out.println("Vinkki tallennettu!\n");
                break;
            case "list":
                for (Tip tip : tipService.getAll()) {
                    System.out.println("\nId: " + tip.getId());
                    System.out.println("Otsikko: " + tip.getTitle());
                    System.out.println("Url: " + tip.getUrl() + "\n");
                }
                break;
            case "quit":
                return;
            default:
                System.out.println("Komentoa '" + cmd + "' ei ole");
            }
            

        }
    }
}
