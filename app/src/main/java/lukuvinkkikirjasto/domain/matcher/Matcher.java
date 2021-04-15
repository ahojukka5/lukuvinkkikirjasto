package lukuvinkkikirjasto.domain.matcher;
import lukuvinkkikirjasto.domain.Tip;

public interface Matcher {
    boolean matches(Tip tip);
}