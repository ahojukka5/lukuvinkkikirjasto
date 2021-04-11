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
}
