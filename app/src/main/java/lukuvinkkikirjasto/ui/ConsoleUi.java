package lukuvinkkikirjasto.ui;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.domain.matcher.Matcher;
import lukuvinkkikirjasto.domain.matcher.TitleContains;
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
            System.out.println("remove - poista vinkki");
            System.out.println("list - listaa ja suodata vinkkejä");
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
                listTips();
                break;
            case "remove":
                for (Tip tip : tipService.getAll()) {
                    System.out.println(String.format("%3d %s", tip.getId(), tip.getTitle()));
                }
                System.out.println("Valitse id-numero:");
                Integer id = Integer.parseInt(scanner.nextLine());
                Tip tip = tipService.findTipById(id);
                if (tip == null) {
                    System.out.println("Vinkkiä id-numerolla " + id + " ei löytynyt!");
                    break;
                }
                String msg = "Poistetaanko vinkki id-numerolla %d ja otsikolla '%s'? [k/e]";
                System.out.println(String.format(msg, tip.getId(), tip.getTitle()));
                String answer = scanner.nextLine();
                if (answer.equals("k")) {
                    tipService.removeTip(tip);
                    System.out.println("Vinkki " + id + " poistettu!");
                }
                break;
            case "quit":
                return;
            default:
                System.out.println("Komentoa '" + cmd + "' ei ole");
            }
            

        }
    }

    private void listTips() {
        List<Matcher> filters = new ArrayList<Matcher>();

        while (true) {
            for (Tip tip : tipService.matchesAll(filters)) {
                System.out.println("\nId: " + tip.getId());
                System.out.println("Otsikko: " + tip.getTitle());
                System.out.println("Url: " + tip.getUrl() + "\n");
            }
            System.out.println("Komennot:");
            System.out.println("title - suodata otsikon perusteella");
            System.out.println("clear - tyhjennä suodatukset " + filters.toString());
            System.out.println("menu - takaisin päävalikkoon");

            String cmd = scanner.nextLine();
            switch (cmd) {
            case "title":
                System.out.println("Mitä otsikon täytyy sisältää?");
                String titleFilter = scanner.nextLine();
                filters.add(new TitleContains(titleFilter));
                break;
            case "clear":
                filters.clear();
                break;
            case "menu":
                return;
            default:
                System.out.println("Komentoa '" + cmd + "' ei ole");            
            }
        }
    }
}
