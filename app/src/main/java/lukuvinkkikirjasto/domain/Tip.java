package lukuvinkkikirjasto.domain;

import java.util.*;
import java.util.stream.*;
import java.time.*; // handle dates somewhere else?

public class Tip {
    private int id;
    private Map<String, String> data;
    private Set<String> tags;

    public Tip(int id, String title, String url) {
        this.id = id;
        this.data = new HashMap<>();
        this.tags = new HashSet<>();
        setValue("title", title);
        setValue("url", url);
    }

    public int getId() {
        return id;
    }

    public String getValue(String key) {
        return data.get(key);
    }

    public void setValue(String key, String value) {
        data.put(key, value);
    }

    public void removeValue(String key) {
        data.remove(key);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public List<String> getTags() {
        return tags.stream().collect(Collectors.toList());
    }

    public List<String> getFields() {
        return data.keySet().stream().collect(Collectors.toList());
    }

    public String getTitle() {
        return data.getOrDefault("title", "");
    }

    public String getUrl() {
        return data.getOrDefault("url", "");
    }

    public String getReadDate() {
        return data.getOrDefault("read-on", "");
    }

    public boolean isRead() {
        return data.containsKey("read-on");
    }

    public void markRead() {
        setValue("read-on", java.time.LocalDate.now().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tip)) {
            return false;
        }

        Tip rhs = (Tip) other;

        return this.id == rhs.id
            && this.data.equals(rhs.data)
            && this.tags.equals(rhs.tags);
    }
}