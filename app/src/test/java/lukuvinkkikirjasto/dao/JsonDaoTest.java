package lukuvinkkikirjasto.dao;

import com.google.gson.Gson;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void loadFromFile() {
        provider = new FakeFilesystemProvider();
        dao = new JsonDao(PATH, provider);

        provider.setContent("[{\"id\": 1, \"title\": \"aa\", \"url\": \"example.com\"}]");
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
}