package lukuvinkkikirjasto.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

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

    @Test
    public void canAddTags() {
        Tip tip = new Tip(1, "a", "b");
        tip.addTag("test");
        assertTrue(tip.hasTag("test"));
    }

    @Test
    public void canRemoveTags() {
        Tip tip = new Tip(1, "a", "b");
        tip.addTag("test");
        tip.removeTag("test");
        assertFalse(tip.hasTag("test"));
    }

    @Test
    public void canListTags() {
        Tip tip = new Tip(1, "a", "b");
        tip.addTag("hello");
        tip.addTag("world");

        List<String> tags = tip.getTags();
        assertTrue(tags.contains("hello"));
        assertTrue(tags.contains("world"));
    }

    @Test
    public void canAddFields() {
        Tip tip = new Tip(1, "a", "b");
        tip.setValue("some", "value");
        assertEquals(tip.getValue("some"), "value");
    }

    @Test
    public void equalsFailsWithDifferentFields() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "a", "b");

        tip1.setValue("hello", "");

        assertNotEquals(tip1, tip2);
    }

    @Test
    public void equalsFailsWithDifferingData() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "a", "b");

        tip1.setValue("hello", "a");
        tip2.setValue("hello", "");

        assertNotEquals(tip1, tip2);
    }

    @Test
    public void equalsFailsWithDifferingTags() {
        Tip tip1 = new Tip(1, "a", "b");
        Tip tip2 = new Tip(1, "a", "b");
        tip1.addTag("test");

        assertNotEquals(tip1, tip2);
    }

    @Test
    public void canListFields() {
        Tip tip = new Tip(1, "a", "b");
        tip.setValue("some", "value");
        List<String> fields = tip.getFields();

        assertTrue(fields.contains("title"));
        assertTrue(fields.contains("url"));
        assertTrue(fields.contains("some"));
    }

    @Test
    public void removingValueRemovesField() {
        Tip tip = new Tip(1, "a", "b");
        tip.setValue("some", "value");
        tip.removeValue("some");

        assertFalse(tip.getFields().contains("some"));
    }

    @Test
    public void canMarkAsRead() {
        Tip tip = new Tip(1, "a", "b");
        assertFalse(tip.isRead());

        tip.markRead();
        assertTrue(tip.isRead());

        assertNotEquals(tip.getReadDate(), "");
    }
}
