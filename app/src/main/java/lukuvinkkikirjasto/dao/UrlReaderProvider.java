package lukuvinkkikirjasto.dao; 
import java.io.IOException;

public interface UrlReaderProvider {
    public String getTitleFrom(String url) throws IOException;
}