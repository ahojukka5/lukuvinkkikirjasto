// tämä luokka on vain alkuun pääsemiseen, oikeasti käytettävä json-talennusta
package lukuvinkkikirjasto.dao;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;

public class InMemoryTipDao implements TipDao {
    List<Tip> tips;
    int id;

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

    @Override
    public void remove(Tip tip) {
        tips.remove(tip);
    }

    @Override
    public Tip findById(Integer id) {
        for (Tip tip : tips) {
            if (tip.getId() == id) {
                return tip;
            }
        }
        return null;
    }
}