package lukuvinkkikirjasto.domain.matcher;

import static org.junit.Assert.*;

import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.*;
import org.junit.*;

/**
 * Unit tests Levenshtein algorithm and matcher.
 */
public class LevenshteinTest {

    void cmp(int expected, String a, String b) {
        assertEquals(expected, Levenshtein.distance(a, b));
        assertEquals(expected, Levenshtein.distance(b, a));
    }

    @Test
    public void exactMatch() {
        String a = "hello";
        String b = "hello";

        cmp(0, a, b);
    }

    @Test
    public void leftZeroLength() {
        String a = "";
        String b = "world";

        cmp(5, a, b);
    }

    @Test
    public void rightZeroLength() {
        String a = "morose";
        String b = "";

        cmp(6, a, b);
    }

    @Test
    public void oneOffLength() {
        String a = "test";
        String b = "testx";

        cmp(1, a, b);
    }

    @Test
    public void oneOffAtBeginning() {
        String a = "xtest";
        String b = "test";

        cmp(1, a, b);
    }

    @Test
    public void oneOffLengthExtraInMiddle() {
        String a = "world";
        String b = "worxld";

        cmp(1, a, b);
    }

    @Test
    public void oneOffLetterChanged() {
        String a = "carousel";
        String b = "caxousel";

        cmp(1, a, b);
    }

    @Test
    public void differentWord() {
        String a = "aaaaa";
        String b = "bbbbbbb";

        cmp(7, a, b);
    }

    @Test
    public void differentWord2() {
        String a = "aaaaa";
        String b = "bbaabbb";

        cmp(5, a, b);
    }

    @Test
    public void testFuzzyMatch() {
        String query = "testtt";
        Tip tip = new Tip(1, "test", "a");
        Matcher m = new Levenshtein(query, 2);
        assertTrue(m.matches(tip));
    }

    @Test
    public void testFuzzyNoMatch() {
        String query = "texx";
        Tip tip = new Tip(1, "test", "a");
        Matcher m = new Levenshtein(query, 1);
        assertFalse(m.matches(tip));
    }

    @Test
    public void testSwappedLetters() {
        String query = "heslinki";
        Tip tip = new Tip(1, "helsinki", "a");
        Matcher m = new Levenshtein(query, 2);
        assertTrue(m.matches(tip));
    }
}