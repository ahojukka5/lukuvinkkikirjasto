package lukuvinkkikirjasto.dao;

import java.util.*;
import lukuvinkkikirjasto.domain.Tip;

/**
 * In-memory tip DAO.
 */
public class InMemoryTipDao implements TipDao {
    List<Tip> tips;
    int id;

    public InMemoryTipDao() {
        this.tips = new ArrayList<Tip>();
        this.id = 1;
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

    @Override
    public void update(Tip tip) {
        Tip old = findById(tip.getId());
        remove(old);
        create(tip);
    }

    @Override
    public int nextId() {
        return id++;
    }
}
