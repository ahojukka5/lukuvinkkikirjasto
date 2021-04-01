package ui;

import java.util.Scanner;

/**
 * Text User Inferface
 */
public class ConsoleUi {
    private final Scanner scanner;

    public ConsoleUi(Scanner scanner) {
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
