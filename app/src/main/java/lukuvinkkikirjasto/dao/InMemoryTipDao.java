// tämä luokka on vain alkuun pääsemiseen, oikeasti käytettävä json-talennusta
package lukuvinkkikirjasto.dao;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;

public class InMemoryTipDao implements TipDao {
    public List<Tip> tips;

    public InMemoryTipDao() {
        this.tips = new ArrayList<Tip>();
    }

    @Override
    public void create(Tip tip) {
        this.tips.add(tip);
    }

    @Override
    public List<Tip> getAll() {
        return tips;
    }
}