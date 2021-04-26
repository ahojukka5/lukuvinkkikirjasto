package lukuvinkkikirjasto.domain;

import java.io.IOException;

/**
 * Fake UrlReader for testing purposes.
 */
public class FakeUrlReader implements UrlReaderProvider {
    /**
     * Returns "FakeTitle".
     * If there is no 'http' in the url, throws exception.
     */
    public String getTitleFrom(String url) throws IOException {
        if (!url.contains("http")) {
            throw new IOException("Invalid url");
        }
        return "FakeTitle";
    } 
}