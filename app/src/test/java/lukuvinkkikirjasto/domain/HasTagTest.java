package lukuvinkkikirjasto.domain.matcher;

import static org.junit.Assert.*;

import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.*;
import org.junit.*;


/**
 * Tests for HasTag matcher.
 */
public class HasTagTest {

    @Test
    public void matchesTipWithTag() {
        Tip tip = new Tip(0, "a", "b");
        tip.addTag("some");
        Matcher m = new HasTag("some");

        assertTrue(m.matches(tip));
    }

    @Test
    public void noMatchWithoutTag() {
        Tip tip = new Tip(0, "a", "b");
        Matcher m = new HasTag("some");
        assertFalse(m.matches(tip));
    }

}