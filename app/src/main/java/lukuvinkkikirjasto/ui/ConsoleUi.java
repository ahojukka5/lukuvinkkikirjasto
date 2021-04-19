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
            System.out.println("remove - poista vinkki");
            System.out.println("list - listaa vinkit");
            System.out.println("markRead - merkitse vinkki luetuksi");
            System.out.println("quit - lopettaa");
            String cmd = scanner.nextLine().trim().toLowerCase();
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
                    System.out.println("Url: " + tip.getUrl());
                    if (tip.isRead()) {
                        System.out.println("Luettu: " + tip.getReadDate());
                    }
                    System.out.println("");
                }
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
            case "markread":
                markRead();
                break;
            case "quit":
                return;
            default:
                System.out.println("Komentoa '" + cmd + "' ei ole");
            }
        }
    }

    public void markRead() {
        String confirmMsg = "Merkitäänkö vinkki id-numerolla %d ja otsikolla '%s' luetuksi? [k/e]";
        String errMsg = "Vinkkiä id-numerolla '%s' ei löytynyt!";
        String okMsg = "Vinkki %d merkitty luetuksi!";

        System.out.println("Lukemattomat vinkit:");
        tipService.getAll().stream().filter(tip -> !tip.isRead()).forEach(tip -> {
            System.out.println(tip.getId() + ": " + tip.getTitle());
        });

        System.out.println("\nSyötä luetuksi merkattavan vinkin id:");
        String input = scanner.nextLine();
        Tip tip = null;
        int id = 0;

        try {
            id = Integer.parseInt(input);
            tip = tipService.findTipById(id);
            System.out.println(String.format(confirmMsg, tip.getId(), tip.getTitle()));
        } catch (Exception e) {
            System.out.println(String.format(errMsg, input));
            return;
        }

        input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("k")) {
            tip.markRead();
            tipService.updateTip(tip);
            System.out.println(String.format(okMsg, id));
        }
    }
}
