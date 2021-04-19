package lukuvinkkikirjasto.dao;

import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

/**
 * Interface for tip DAO.
 */
public interface TipDao {
    void create(Tip tip);

    void remove(Tip tip);

    void update(Tip tip);

    Tip findById(Integer id);

    List<Tip> getAll();
}
