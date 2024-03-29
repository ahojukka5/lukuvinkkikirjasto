package lukuvinkkikirjasto.domain.matcher;

import lukuvinkkikirjasto.domain.Tip;

/**
 * TitleContains matcher.
 */
public class TitleContains implements Matcher {
    private String query;

    public TitleContains(String query) {
        this.query = query;
    }

    @Override
    public boolean matches(Tip tip) {
        return tip.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public String toString() {
        return "Otsikkossa täsmällinen osa: " + query;
    }

}