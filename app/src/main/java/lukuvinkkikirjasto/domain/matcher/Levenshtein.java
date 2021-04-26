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
        String title = t.getTitle();
        int allowance = Math.max(title.length() - str.length(), 0);

        return distance(title, str) <= distance + allowance;
    }

    static String tail(String s) {
        return s.substring(1);
    }

    static int distance(String left, String right) {
        if (left.length() == 0 || right.length() == 0) {
            return Math.max(left.length(), right.length());
        }

        if (left.charAt(0) == right.charAt(0)) {
            return distance(tail(left), tail(right));
        }

        return 1 + Math.min(
            Math.min(
                distance(tail(left), right),
                distance(left, tail(right))
            ),
            distance(tail(left), tail(right))
        );
    }

    @Override
    public String toString() {
        return "Otsikko sisältää: " + str;
    }
}