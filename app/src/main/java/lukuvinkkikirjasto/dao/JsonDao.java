package lukuvinkkikirjasto.dao;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;

public class JsonDao implements TipDao {
    InMemoryTipDao cache;
    String fileName;

    public JsonDao(String fileName) {
        cache = new InMemoryTipDao();
        this.fileName = fileName;
        open();
    }

    private void open() {
        try {
            FileReader reader = new FileReader(fileName);
            Tip[] tips = new Gson().fromJson(reader, Tip[].class);

            for (Tip tip : tips) {
                cache.create(tip);
            }
        } catch(Exception e) { }
    }

    private void save() {
        List<Tip> tips = cache.getAll();
        String data = new Gson().toJson(tips);
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
            writer.close();
        } catch(Exception e) { }
    }

    public void create(Tip tip) {
        cache.create(tip);
        save();
    }

    public List<Tip> getAll() {
        return cache.getAll();
    }
}