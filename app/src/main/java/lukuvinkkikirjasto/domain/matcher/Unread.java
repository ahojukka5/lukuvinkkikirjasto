package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * Unread matcher.
 */
public class Unread implements Matcher {
    @Override
    public boolean matches(Tip p) {
        return !p.isRead();
    }

    @Override
    public String toString() {
        return "Lukematon";
    }
}