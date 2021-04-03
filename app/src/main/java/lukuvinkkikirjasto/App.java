package lukuvinkkikirjasto;

import java.util.Scanner;

import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.ConsoleUi;
import lukuvinkkikirjasto.dao.JsonDao;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TipService tipService = new TipService(new JsonDao("file.txt"));
        new ConsoleUi(scanner, tipService).start();
    }
}
