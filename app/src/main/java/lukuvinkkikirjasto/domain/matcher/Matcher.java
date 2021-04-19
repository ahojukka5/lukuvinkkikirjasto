package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * Matcher interface.
 */
public interface Matcher {
    boolean matches(Tip tip);
}
