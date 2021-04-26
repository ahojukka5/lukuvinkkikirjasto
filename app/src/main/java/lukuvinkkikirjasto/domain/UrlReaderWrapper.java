package lukuvinkkikirjasto.domain;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class UrlReaderWrapper implements UrlReaderProvider {
    /**
     * Returns the title of a webpage.
     *
     * @param url url of the webpage
     */
    public String getTitleFrom(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        return title;
    }
}