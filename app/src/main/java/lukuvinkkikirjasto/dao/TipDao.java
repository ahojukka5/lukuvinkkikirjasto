package lukuvinkkikirjasto.dao;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    void create(Tip tip);
    List<Tip> getAll();
}