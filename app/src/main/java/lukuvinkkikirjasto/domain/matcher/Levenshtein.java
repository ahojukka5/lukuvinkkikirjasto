package lukuvinkkikirjasto.domain.matcher;

import java.lang.Math;
import lukuvinkkikirjasto.domain.Tip;

/**
 * Fuzzy title matcher.
 */
public class Levenshtein implements Matcher {
    String str;
    int distance;

    public Levenshtein(String str, int distance) {
        this.str = str.toLowerCase().trim();
        this.distance = distance;
    }

    @Override
    public boolean matches(Tip t) {
        String title = t.getTitle().toLowerCase();
        int allowance = Math.max(title.length() - str.length(), 0);

        return distance(title, str) <= distance + allowance;
    }

    static String tail(String s) {
        return s.substring(1);
    }

    static int distance(String left, String right) {
        int llen = left.length();
        int rlen = right.length();
        int[][] d = new int[llen + 1][rlen + 1];

        for (int l = 1; l <= llen; l++) {
            d[l][0] = l;
        }

        for (int r = 1; r <= rlen; r++) {
            d[0][r] = r;
        }

        for (int l = 1; l <= llen; l++) {
            for (int r = 1; r <= rlen; r++) {
                int cost = left.charAt(l - 1) == right.charAt(r - 1) ? 0 : 1;
                d[l][r] = Math.min(
                    Math.min(
                        d[l - 1][r] + 1,
                        d[l][r - 1] + 1
                    ),
                    d[l - 1][r - 1] + cost
                );
            }
        }

        return d[llen][rlen];
    }

    @Override
    public String toString() {
        return "Otsikko sisältää: " + str;
    }
}