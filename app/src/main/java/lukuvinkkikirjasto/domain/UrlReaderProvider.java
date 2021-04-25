package lukuvinkkikirjasto.domain; 
import java.io.IOException;

public interface UrlReaderProvider {
    public String getTitleFrom(String url) throws IOException;
}