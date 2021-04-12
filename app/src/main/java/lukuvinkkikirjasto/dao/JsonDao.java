package lukuvinkkikirjasto.dao;
import com.google.gson.Gson;
import java.util.*;
import lukuvinkkikirjasto.domain.Tip;

public class JsonDao implements TipDao {
    InMemoryTipDao cache;
    String fileName;
    FilesystemProvider fileSystem;

    public JsonDao(String fileName) {
        cache = new InMemoryTipDao();
        this.fileName = fileName;
        fileSystem = new FilesystemWrapper();
        open();
    }

    JsonDao(String fileName, FilesystemProvider provider) {
        cache = new InMemoryTipDao();
        this.fileName = fileName;
        fileSystem = provider;
        open();
    }

    private void open() {
        try {
            String data = fileSystem.read(fileName);
            Tip[] tips = new Gson().fromJson(data, Tip[].class);

            for (Tip tip : tips) {
                cache.create(tip);
            }
        } catch(Exception e) { }
    }

    private void save() {
        List<Tip> tips = cache.getAll();
        String data = new Gson().toJson(tips);
        fileSystem.write(fileName, data);
    }

    public void create(Tip tip) {
        cache.create(tip);
        save();
    }

    public List<Tip> getAll() {
        return cache.getAll();
    }
}