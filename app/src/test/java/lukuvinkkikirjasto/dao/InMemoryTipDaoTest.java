package lukuvinkkikirjasto.dao;

import org.junit.*;
import static org.junit.Assert.*;
import lukuvinkkikirjasto.domain.Tip;
import java.util.*;

public class InMemoryTipDaoTest {
    InMemoryTipDao dao;

    @Before
    public void initialze() {
        dao = new InMemoryTipDao();
    }

    @Test
    public void creatingTipWorks() {
        Tip tip = new Tip(1, "aa", "example.com");
        dao.create(tip);
        assertTrue(dao.getAll().contains(tip));
    }

    @Test
    public void creatingTwoTipsWorks() {
        Tip tip1 = new Tip(1, "aa", "example.com");
        Tip tip2 = new Tip(2, "bb", "example.com");
        dao.create(tip1);
        dao.create(tip2);
        List<Tip> tips = dao.getAll();
        assertTrue(tips.contains(tip1));
        assertTrue(tips.contains(tip2));
    }
}