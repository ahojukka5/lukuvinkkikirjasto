package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import java.util.*;
import lukuvinkkikirjasto.dao.InMemoryTipDao;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.*;
import org.junit.*;

/**
 * Unit tests for TipService.
 */
public class TipServiceTest {
    private TipService tipService;
    private InMemoryTipDao dao;

    @Before
    public void initialize() {
        dao = new InMemoryTipDao();
        tipService = new TipService(dao);
    }

    @Test
    public void createTipWorks() {
        tipService.createTip("aa", "example.com");
        assertTrue(tipService.getAll().get(0).getTitle().equals("aa"));
        assertTrue(tipService.getAll().get(0).getUrl().equals("example.com"));
    }

    @Test
    public void matchesAllWorksWithTitleContains() {
        Tip tip1 = new Tip(1, "yykaakoo", "example1.com");
        Tip tip2 = new Tip(1, "kookaayy", "example2.com");
        Tip tip3 = new Tip(1, "jeejeejee", "example3.com");
        dao.create(tip1);
        dao.create(tip2);
        dao.create(tip3);

        List<Matcher> matcherList = new ArrayList<Matcher>();

        assertTrue(tipService.matchesAll(matcherList).contains(tip1));
        assertTrue(tipService.matchesAll(matcherList).contains(tip2));
        assertTrue(tipService.matchesAll(matcherList).contains(tip3));

        matcherList.add(new TitleContains("yy"));
        assertTrue(tipService.matchesAll(matcherList).contains(tip1));
        assertTrue(tipService.matchesAll(matcherList).contains(tip2));
        assertFalse(tipService.matchesAll(matcherList).contains(tip3));

        matcherList.add(new TitleContains("kaakoo"));
        assertTrue(tipService.matchesAll(matcherList).contains(tip1));
        assertFalse(tipService.matchesAll(matcherList).contains(tip2));
        assertFalse(tipService.matchesAll(matcherList).contains(tip3));

        matcherList.add(new TitleContains("jepulis"));
        assertTrue(tipService.matchesAll(matcherList).size() == 0);
    }
    
}
