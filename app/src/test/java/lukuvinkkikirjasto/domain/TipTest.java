package lukuvinkkikirjasto.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TipTest {

    @Test
    public void testCanCreateTip() {
        Tip tip = new Tip(1, "otsikko", "www.otsikko.com");
        assertEquals(tip.getId(), 1);
        assertEquals(tip.getTitle(), "otsikko");
        assertEquals(tip.getUrl(), "www.otsikko.com");
    }

    @Test
    public void equalsIdMismatch() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(2, "a", "b");

        assertFalse(tip1.equals(tip2));
    }

    @Test
    public void equalsTitleMismatch() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "c", "b");

        assertFalse(tip1.equals(tip2));
    }

    @Test
    public void equalsUrlMismatch() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "a", "c");

        assertFalse(tip1.equals(tip2));
    }

    @Test
    public void equalsMatches() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "a", "b");

        assertTrue(tip1.equals(tip2));
    }
}
