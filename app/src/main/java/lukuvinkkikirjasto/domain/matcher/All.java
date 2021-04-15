package lukuvinkkikirjasto.domain.matcher;
import lukuvinkkikirjasto.domain.Tip;

public class All implements Matcher {
    @Override
    public boolean matches(Tip p) {
        return true;
    }
}