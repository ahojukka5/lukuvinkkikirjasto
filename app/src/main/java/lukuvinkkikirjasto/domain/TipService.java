package lukuvinkkikirjasto.domain;

import java.io.IOException;
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
    private UrlReaderProvider urlReader;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
        this.urlReader = new UrlReaderWrapper();
    }

    public TipService(TipDao tipDao, UrlReaderProvider urlReader) {
        this.tipDao = tipDao;
        this.urlReader = urlReader;
    }

    /**
     * Create new tip.
     *
     * @param title title of tip
     * @param url url of tip
     */
    public void createTip(String title, String url) {
        int id = tipDao.nextId();
        Tip tip = new Tip(id, title, url);
        tipDao.create(tip);
    }

    /**
     * Create new tip with a title fetched from a url.
     *
     * @param url url of tip
     */
    public Tip createTipFromUrl(String url) throws IOException {
        int id = tipDao.nextId();
        String title = urlReader.getTitleFrom(url);

        Tip tip = new Tip(id, title, url);
        tipDao.create(tip);
        return tip;
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
