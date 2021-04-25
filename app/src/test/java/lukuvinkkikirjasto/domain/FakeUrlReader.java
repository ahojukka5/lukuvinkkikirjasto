package lukuvinkkikirjasto.domain;

import java.io.IOException;

class FakeUrlReader implements UrlReaderProvider {
    public String getTitleFrom(String url) throws IOException {
        return "FakeTitle";
    } 
}