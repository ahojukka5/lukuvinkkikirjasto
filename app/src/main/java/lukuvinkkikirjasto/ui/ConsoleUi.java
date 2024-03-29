package lukuvinkkikirjasto.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.domain.matcher.HasTag;
import lukuvinkkikirjasto.domain.matcher.Levenshtein;
import lukuvinkkikirjasto.domain.matcher.Matcher;
import lukuvinkkikirjasto.domain.matcher.Read;
import lukuvinkkikirjasto.domain.matcher.TitleContains;
import lukuvinkkikirjasto.domain.matcher.Unread;

/**
 * Text User Inferface.
 */
public class ConsoleUi {
    private final Scanner scanner;
    private final TipService tipService;

    public ConsoleUi(Scanner scanner, TipService tipService) {
        this.scanner = scanner;
        this.tipService = tipService;
    }

    /**
     * Start Console UI.
     */
    public void start() {
        while (true) {
            System.out.println("Komennot:");
            System.out.println("add - lisää vinkki");
            System.out.println("addUrl - lisää vinkki pelkän urlin perusteella");
            System.out.println("edit - muokkaa vinkkiä");
            System.out.println("addTags - lisää vinkille tägejä");
            System.out.println("remove - poista vinkki");
            System.out.println("list - listaa ja suodata vinkkejä");
            System.out.println("markRead - merkitse vinkki luetuksi");
            System.out.println("quit - lopettaa");
            String cmd = scanner.nextLine().trim().toLowerCase();
            switch (cmd) {
            case "add":
                add();
                break;
            case "addurl":
                addUrl();
                break;
            case "edit":
                editTip();
                break;
            case "addtags":
                addTags();
                break;
            case "list":
                listTips();
                break;
            case "remove":
                removeTip();
                break;
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

    /**
     * Add tags to tip.
     */
    public void addTags() {
        Tip tip = chooseTip();
        if (tip == null) {
            return;
        }
        String tags = String.join(", ", tip.getTags());
        System.out.println("Nykyiset tägit: " + tags);
        System.out.print("Anna uusi tag: ");
        String newTag = scanner.nextLine();
        tip.addTag(newTag);
        tipService.updateTip(tip);
        System.out.println(String.format("Tägi '%s' on lisätty vinkille %d!", newTag, tip.getId()));
    }

    /**
     * List all tips and asks to choose one tip based on id number.
     *
     * @return Tip or null if no tip found with that id number.
     */
    public Tip chooseTip() {
        for (Tip tip : tipService.getAll()) {
            System.out.println(String.format("%3d %s", tip.getId(), tip.getTitle()));
        }
        System.out.println("Valitse id-numero:");
        Integer id = Integer.parseInt(scanner.nextLine());
        Tip tip = tipService.findTipById(id);
        if (tip == null) {
            System.out.println("Vinkkiä id-numerolla " + id + " ei löytynyt!");
        }
        return tip;
    }

    /**
     * Edit tip.
     */
    public void editTip() {
        Tip tip = chooseTip();
        if (tip == null) {
            return;
        }

        // Go each field value one by one, and update with new values if
        // non-empty string is given.
        String newValue;
        for (String field : tip.getFields()) {
            String fieldValue = tip.getValue(field);
            System.out.print(String.format("%s [%s]: ", field, fieldValue));
            newValue = scanner.nextLine();
            if (newValue.length() > 0) {
                tip.setValue(field, newValue);
            }
        }

        // List all tags attached to tip and ask for replacement as a
        // comma-separated list
        String tags = String.join(", ", tip.getTags());
        System.out.print(String.format("%s [%s]: ", "Tags", tags));
        newValue = scanner.nextLine();
        if (newValue.length() > 0) {
            for (String tag : tip.getTags()) {
                tip.removeTag(tag);
            }
            for (String tag : newValue.split(",")) {
                tip.addTag(tag.trim());
            }
        }

        tipService.updateTip(tip);
        System.out.println("Vinkki " + tip.getId() + " päivitetty!");
    }

    /**
     * Remove tip.
     */
    public void removeTip() {
        Tip tip = chooseTip();
        if (tip == null) {
            return;
        }
        String msg = "Poistetaanko vinkki id-numerolla %d ja otsikolla '%s'? [k/e]";
        System.out.println(String.format(msg, tip.getId(), tip.getTitle()));
        String answer = scanner.nextLine();
        if (answer.equals("k")) {
            tipService.removeTip(tip);
            System.out.println("Vinkki " + tip.getId() + " poistettu!");
        }
    }

    /**
     * Mark tip as read.
     */
    public void markRead() {
        String confirmMsg = "Merkitäänkö vinkki id-numerolla %d ja otsikolla '%s' luetuksi? [k/e]";
        String errMsg = "Vinkkiä id-numerolla '%s' ei löytynyt!";
        String okMsg = "Vinkki %d merkitty luetuksi!";

        System.out.println("Lukemattomat vinkit:");
        for (Tip tip : tipService.matches(new Unread())) {
            System.out.println(tip.getId() + ": " + tip.getTitle());
        }

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

    private void listTips() {
        List<Matcher> filters = new ArrayList<Matcher>();

        while (true) {
            System.out.println("Aktiiviset suodatukset: " + filters.toString());

            for (Tip tip : tipService.matchesAll(filters)) {
                System.out.println("\nId: " + tip.getId());
                System.out.println("Otsikko: " + tip.getTitle());
                System.out.println("Url: " + tip.getUrl());
                System.out.println("Tägit: " + String.join(", ", tip.getTags()));
                if (tip.isRead()) {
                    System.out.println("Luettu: " + tip.getReadDate());
                }

                System.out.println('\n');
            }
            System.out.println("Komennot:");
            System.out.println("title - suodata otsikon perusteella");
            System.out.println("titleExact - suodata otsikon perusteella tarkat osumat");
            System.out.println("read - suodata luetut");
            System.out.println("unread - suodata lukemattomat");
            System.out.println("tag - suodata tagin sisältäviä");
            System.out.println("undo - poista viimeisin suodatin");
            System.out.println("clear - tyhjennä suodatukset ");
            System.out.println("menu - takaisin päävalikkoon");

            String cmd = scanner.nextLine().trim();
            switch (cmd) {
            case "title":
                System.out.println("Mitä otsikosta haetaan?");
                String srch = scanner.nextLine();
                filters.add(new Levenshtein(srch, 2));
                break;
            case "titleExact":
                System.out.println("Mitä otsikon täytyy sisältää?");
                String titleFilter = scanner.nextLine().trim();
                filters.add(new TitleContains(titleFilter));
                break;
            case "read":
                filters.add(new Read());
                break;
            case "unread":
                filters.add(new Unread());
                break;
            case "tag":
                System.out.println("Minkä tagin vinkin tulee sisältää?");
                String tag = scanner.nextLine().trim();
                filters.add(new HasTag(tag));
                break;
            case "undo":
                if (filters.size() > 0) {
                    filters.remove(filters.size() - 1);
                }
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
    
    private void add() {
        System.out.println("Otsikko?");
        String title = scanner.nextLine();
        while (title.length() < 3) {
            System.out.println("Anna pidempi otsikko");
            title = scanner.nextLine();
        }
        System.out.println("Url?");
        String url = scanner.nextLine();

        tipService.createTip(title, url);

        System.out.println("Vinkki tallennettu!\n");
    }

    private void addUrl() {
        System.out.println("Url?");
        String url = scanner.nextLine();
        Tip newTip;
        String newTitle = "";

        try {
            newTip = tipService.createTipFromUrl(url);
            System.out.println("Haettiin otsikko: " + newTip.getTitle());
            System.out.println("Korvaa otsikko (tyhjä ohittaa): ");
            newTitle = scanner.nextLine();

        } catch (Exception e) {
            newTip = tipService.createTip("", url);
            System.out.println("Otsikon haku URL:sta epäonnistui:");
            System.out.println(e);
            System.out.println("Anna otsikko: ");
            while (newTitle.length() < 3) {
                System.out.println("Anna pidempi otsikko");
                newTitle = scanner.nextLine();
            }
        }

        if (!newTitle.equals("")) {
            newTip.setValue("title", newTitle);
            tipService.updateTip(newTip);
            System.out.println("Otsikoksi päivitettiin: " + newTitle);
        }

        System.out.println("Vinkki tallennettu!\n");
    }
}
