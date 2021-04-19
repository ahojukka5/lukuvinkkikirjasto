package lukuvinkkikirjasto.domain.matcher;

import static org.junit.Assert.*;

import java.util.*;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.*;
import org.junit.*;


/**
 * Unit tests for read matcher.
 */
public class ReadTest {
    @Test
    public void matchesReadTip() {
        Tip tip = new Tip(0, "a", "b");
        tip.markRead();

        Matcher match = new Read();
        assertTrue(match.matches(tip));
    }

    @Test
    public void matchFailsOnUnread() {
        Tip tip = new Tip(0, "a", "b");

        Matcher match = new Read();
        assertFalse(match.matches(tip));
    }
}