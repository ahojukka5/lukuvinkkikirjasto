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
    
    @Override
    public void create(Tip tip) {
        cache.create(tip);
        save();
    }

    @Override
    public List<Tip> getAll() {
        return cache.getAll();
    }

    @Override
    public void remove(Tip tip) {
        cache.remove(tip);
        save();
    }

    @Override
    public Tip findById(Integer id) {
        return cache.findById(id);
    }
}