package lukuvinkkikirjasto;

import java.util.Scanner;
import lukuvinkkikirjasto.dao.JsonDao;
import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.ConsoleUi;

/**
 * Main App class.
 */
public class App {

    /**
     * Start app with console UI.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TipService tipService = new TipService(new JsonDao("file.txt"));
        new ConsoleUi(scanner, tipService).start();
    }
}
