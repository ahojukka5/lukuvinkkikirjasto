// Sovelluslogiikasta vastaava luokka
package lukuvinkkikirjasto.domain;

import java.util.List;
import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
    private TipDao tipDao;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    public void createTip(String title, String url) {
        int id = tipDao.getAll().size() + 1;
        Tip tip = new Tip(id, title, url);
        tipDao.create(tip);
    }

    public List<Tip> getAll() {
        return tipDao.getAll();
    }

    public Tip findTipById(Integer id) {
        return tipDao.findById(id);
    }

    public void removeTip(Tip tip) {
        tipDao.remove(tip);
    }

    public void updateTip(Tip tip) {
        tipDao.update(tip);
    }
}
