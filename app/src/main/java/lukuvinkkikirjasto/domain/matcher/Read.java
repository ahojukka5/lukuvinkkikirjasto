package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * Read matcher.
 */
public class Read implements Matcher {
    @Override
    public boolean matches(Tip p) {
        return p.isRead();
    }

    @Override
    public String toString() {
        return "Luettu";
    }
}