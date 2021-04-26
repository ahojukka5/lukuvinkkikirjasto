package lukuvinkkikirjasto;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private static final String FILENAME = "test.txt";

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
        new File(FILENAME).delete();
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Given("^command add is selected$")
    public void commandAddSelected() throws Throwable {
        inputLines.add("add");
    }

    @Given("^command addUrl is selected$")
    public void commandAddUrlSelected() throws Throwable {
        inputLines.add("addUrl");
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

    @When("url {string} is entered")
    public void validUrlIsEntered(String url) {
        inputLines.add(url);
    }

    @When("enter is pressed")
    public void enterPressed() {
        inputLines.add("");
    }

    /**
     * Check that console is giving expected output.
     */
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        inputLines.add("menu");
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao(FILENAME), new FakeUrlReader());

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
        TipService tipService = new TipService(new JsonDao(FILENAME));

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

    @Given("^command markRead is selected$")
    public void commandMarkReadSelected() {
        inputLines.add("markRead");
    }

    @When("id {string} is entered")
    public void idIsEntered(String id) {
        inputLines.add(id);
    }

    @When("title {string} is entered")
    public void titleIsEntered(String title) {
        inputLines.add(title);
    }

    @When("^operation is confirmed$")
    public void operationIsConfirmed() {
        inputLines.add("k");
    }

    @When("^operation is cancelled$")
    public void operationIsCancelled() {
        inputLines.add("e");
    }

    @Given("^command title is selected$")
    public void commandFilterTitleSelected() {
        inputLines.add("title");
    }

    @Given("command titleExact is selected$")
    public void commandFilterTitleExactSelected() {
        inputLines.add("titleExact");
    }

    @When("filter {string} is entered")
    public void filterIsEntered(String filter) {
        inputLines.add(filter);
    }

    /**
     * Mark specified tip as read given the program is in main menu.
     */
    @Given("tip with id {string} is marked read")
    public void markTipRead(String id) {
        inputLines.add("markread");
        inputLines.add(id);
        inputLines.add("k");
    }

    @Given("^list mode is selected$")
    public void selectListMode() {
        inputLines.add("list");
    }

    @When("^user filters by read tips$")
    public void filterByReadTips() {
        inputLines.add("read");
    }

    @When("^user filters by unread tips$")
    public void filterByUnreadTips() {
        inputLines.add("unread");
    }

    @When("^command remove is selected$")
    public void commandRemoveSelected() {
        inputLines.add("remove");
    }

    @When("command edit is selected")
    public void commandEditSelected() {
        inputLines.add("edit");
    }

    @When("empty line is given")
    public void emptyLine() {
        inputLines.add("");
    }

    @When("{string} is written to console")
    public void writeString(String s) {
        inputLines.add(s);
    }

    /**
     * Remove tip with specified id from database.
     */
    @Given("tip with id {int} is removed")
    public void removeTipWithId(int id) {
        inputLines.add("remove");
        inputLines.add("" + id);
        inputLines.add("k");
    }

    /**
     * Run program and count how many lines contain specified string.
     */
    @Then("output has line with text {string} {int} times")
    public void outputHasCountOfString(String s, int count) {
        inputLines.add("menu");
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao(FILENAME));

        new ConsoleUi(scanner, tipService).start();

        int found = 0;

        for (String line : outContent.toString().split("\n")) {
            if (line.contains(s)) {
                found++;
            }
        }

        assertEquals(count, found);
    }

    /**
     * Run program and count how many lines match specified string.
     */
    @Then("output has line {string} {int} times")
    public void outputHasLineTimes(String s, int count) {
        inputLines.add("menu");
        inputLines.add("quit");
        Scanner scanner = new Scanner(join(inputLines));
        TipService tipService = new TipService(new JsonDao(FILENAME));

        new ConsoleUi(scanner, tipService).start();

        int found = 0;

        for (String line : outContent.toString().split("\n")) {
            if (line.equals(s)) {
                found++;
            }
        }

        assertEquals(0, found);
    }
}
