package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;


/**
 * Matcher which checks for specified tag.
 */
public class HasTag implements Matcher {
    String tag;

    public HasTag(String tag) {
        this.tag = tag;
    }

    public boolean matches(Tip tip) {
        return tip.hasTag(tag);
    }

    @Override
    public String toString() {
        return "Tag: " + tag;
    }
}