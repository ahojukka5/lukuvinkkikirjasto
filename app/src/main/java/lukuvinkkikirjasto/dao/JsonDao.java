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
            StoredData db = new Gson().fromJson(data, StoredData.class);

            for (Tip tip : db.tips) {
                cache.create(tip);
            }

            cache.id = db.id;
        } catch (Exception e) { }
    }

    void save() {
        StoredData db = new StoredData();
        db.tips = cache.tips;
        db.id = cache.id;

        String data = new Gson().toJson(db);
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

    @Override
    public void update(Tip tip) {
        cache.update(tip);
        save();
    }
}