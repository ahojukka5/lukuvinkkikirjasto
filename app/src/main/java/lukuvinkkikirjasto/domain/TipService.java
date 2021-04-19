package lukuvinkkikirjasto.domain;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.dao.TipDao;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.matcher.All;
import lukuvinkkikirjasto.domain.matcher.And;
import lukuvinkkikirjasto.domain.matcher.Matcher;

/**
 * TipService class.
 */
public class TipService {
    private TipDao tipDao;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    /**
     * Create new tip.
     *
     * @param title title of tip
     * @param url url of tip
     */
    public void createTip(String title, String url) {
        int id = tipDao.getAll().size() + 1;
        Tip tip = new Tip(id, title, url);
        tipDao.create(tip);
    }

    public List<Tip> getAll() {
        return tipDao.getAll();
    }

    /**
     * Return all matches of tip.
     *
     * @param matcher is iterable of matchers
     * @return matches
     */
    public List<Tip> matches(Matcher matcher) {
        ArrayList<Tip> matches = new ArrayList<Tip>();
        
        for (Tip tip : tipDao.getAll()) {
            if (matcher.matches(tip)) {
                matches.add(tip);
            }            
        }
        return matches;
    }

    /**
     * Check does tip match to all matchers.
     *
     * @param matchers is iterable of matchers
     * @return boolean value
     */
    public List<Tip> matchesAll(List<Matcher> matchers) {
        Matcher finalMatcher = new All();

        for (Matcher matcher : matchers) {
            finalMatcher = new And(finalMatcher, matcher);
        }

        return matches(finalMatcher);
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
