package lukuvinkkikirjasto;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkikirjasto.dao.*;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.ui.*;

/**
 * Step definitions.
 */
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

    /**
     * Setup.
     */
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

    @Given("^command list is selected$")
    public void commandListSelected() throws Throwable {
        inputLines.add("list");
    }

    @When("title {string} and url {string} are entered")
    public void titleAndUrlAreEntered(String title, String url) {
        inputLines.add(title);
        inputLines.add(url);
    }

    /**
     * Check that console is giving expected output.
     */
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        inputLines.add("menu");
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao("test.txt"));

        new ConsoleUi(scanner, tipService).start();

        assertTrue(outContent.toString().contains(expectedOutput));
    }

    /**
     * Check that console is giving expected filtered results.
     */
    @Then("filtered results should contain {string} but not {string}")
    public void filteredShouldContain(String expectedOutput, String notExpectedOutput) {
        inputLines.add("menu");
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao("test.txt"));

        new ConsoleUi(scanner, tipService).start();

        String allContents = outContent.toString();
        String[] parts = allContents.split("Mitä otsikon täytyy sisältää?");

        String contentsAfterFilter = parts[parts.length - 1];

        assertTrue(contentsAfterFilter.toString().contains(expectedOutput));
        assertFalse(contentsAfterFilter.toString().contains(notExpectedOutput));
    }

    /**
     * Add tip with title and url.
     */
    @Given("tip with title {string} and url {string} is created")
    public void tipWithTitleAndUrlIsCreated(String title, String url) {
        inputLines.add("add");
        inputLines.add(title);
        inputLines.add(url);
    }

    @Given("command markRead is selected$")
    public void commandMarkReadSelected() {
        inputLines.add("markRead");
    }

    @When("id {string} is entered")
    public void idIsEntered(String id) {
        inputLines.add(id);
    }

    @When("operation is confirmed")
    public void operationIsConfirmed() {
        inputLines.add("k");
    }

    @Given("command title is selected$")
    public void commandFilterSelected() {
        inputLines.add("title");
    }

    @When("filter {string} is entered")
    public void filterIsEntered(String filter) {
        inputLines.add(filter);
    }

}
