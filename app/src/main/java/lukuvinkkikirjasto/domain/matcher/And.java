package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * And matcher.
 */
public class And implements Matcher {
    private Matcher[] matchers;

    public And(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Tip p) {
        for (Matcher matcher : matchers) {
            if (!matcher.matches(p)) {
                return false;
            }
        }

        return true;
    }
}
