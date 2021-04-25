package lukuvinkkikirjasto.dao; 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class UrlReaderWrapper implements UrlReaderProvider {
    public String getTitleFrom(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        return title;
    }
}