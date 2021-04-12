package lukuvinkkikirjasto;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

import lukuvinkkikirjasto.dao.*;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.ui.*;

public class Stepdefs {
    private List<String> inputLines;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    // joins list into a string separated by line breaks
    String join(List<String> args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));

        inputLines = new ArrayList<>();
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Given("^command add is selected$")
    public void commandAddSelected() throws Throwable {
        inputLines.add("add");
    }

    @When("title {string} and url {string} are entered")
    public void titleAndUrlAreEntered(String title, String url) {
        inputLines.add(title);
        inputLines.add(url);
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao("test.txt"));

        new ConsoleUi(scanner, tipService).start();

        assertTrue(outContent.toString().contains(expectedOutput));
    }
}