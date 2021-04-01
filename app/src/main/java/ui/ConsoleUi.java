package lukuvinkkikirjasto;

import java.util.Scanner;

/**
 * Text User Inferface
 */
public class TUI {
    private final Scanner scanner;

    public TUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("Komennot:");
            String cmd = scanner.nextLine();
            switch (cmd) {
            case "lopeta":
                return;
            }
        }
    }
}
