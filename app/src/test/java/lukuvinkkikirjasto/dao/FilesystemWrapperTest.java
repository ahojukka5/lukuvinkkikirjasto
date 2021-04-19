package lukuvinkkikirjasto.dao;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Tests for file system wrapper.
 */
public class FilesystemWrapperTest {
    FilesystemWrapper wrapper;
    static String PATH = "test.txt";

    @Before
    public void initialize() {
        wrapper = new FilesystemWrapper();
    }

    @Test
    public void filesystemWrapperIoTest1() {
        String s = new String("Hello World");
        wrapper.write(PATH, s);
        assertEquals(s, wrapper.read(PATH));
    }

    @Test
    public void filesystemWrapperIoTest2() {
        String s = new String("Another String");
        wrapper.write(PATH, s);
        assertEquals(s, wrapper.read(PATH));
    }
}