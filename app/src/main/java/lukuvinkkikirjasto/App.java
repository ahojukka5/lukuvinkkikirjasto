package lukuvinkkikirjasto;

import java.util.Scanner;

import lukuvinkkikirjasto.domain.TipService;
import lukuvinkkikirjasto.ui.ConsoleUi;
import lukuvinkkikirjasto.dao.InMemoryTipDao;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TipService tipService = new TipService(new InMemoryTipDao());
        new ConsoleUi(scanner, tipService).start();
    }
}
