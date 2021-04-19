package lukuvinkkikirjasto.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;
import org.junit.*;

/**
 * Tests for Json DAO.
 */
public class JsonDaoTest {
    FakeFilesystemProvider provider;
    JsonDao dao;
    static String PATH = "filename.txt";

    @Test
    public void saveToFile() {
        provider = new FakeFilesystemProvider();
        dao = new JsonDao(PATH, provider);
        Tip tip = new Tip(1, "aa", "example.com");
        dao.create(tip);
        dao = new JsonDao(PATH, provider);
        assertTrue(dao.getAll().contains(tip));
    }

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    public void loadFromFile() {
        provider = new FakeFilesystemProvider();
        String content = "{\"tips\":[{\"id\":1,\"data\":{\"title\":\"aa\",\"url\":\"example.com\"},\"tags\":[]}],\"id\":0}";
        provider.setContent(content);
        dao = new JsonDao(PATH, provider);
        Tip tip = new Tip(1, "aa", "example.com");
        assertTrue(dao.getAll().contains(tip));
    }

    @Test
    public void saveToCorrectPath() {
        provider = mock(FakeFilesystemProvider.class);
        dao = new JsonDao(PATH, provider);
        Tip tip = new Tip(1, "aa", "example.com");
        dao.create(tip);

        verify(provider).write(eq(PATH), anyString());
    }

    @Test
    public void idLoadsCorrectly() {
        provider = new FakeFilesystemProvider();
        provider.setContent("{\"tips\":[],\"id\":42}");
        dao = new JsonDao(PATH, provider);
        assertEquals(42, dao.cache.id);
    }

    @Test
    public void idSavesCorrectly() {
        provider = new FakeFilesystemProvider();
        dao = new JsonDao(PATH, provider);
        dao.cache.id = 33;
        dao.save();

        dao = new JsonDao(PATH, provider);
        assertEquals(33, dao.cache.id);
    }
}