package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * All matcher.
 */
public class All implements Matcher {
    @Override
    public boolean matches(Tip p) {
        return true;
    }
}