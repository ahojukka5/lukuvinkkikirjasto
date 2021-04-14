package lukuvinkkikirjasto.dao;

import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    void create(Tip tip);

    void remove(Tip tip);

    Tip findById(Integer id);

    List<Tip> getAll();
}
