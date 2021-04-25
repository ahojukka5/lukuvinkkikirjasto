package lukuvinkkikirjasto.domain;

import java.io.IOException;

interface UrlReaderProvider {
    public String getTitleFrom(String url) throws IOException;
}