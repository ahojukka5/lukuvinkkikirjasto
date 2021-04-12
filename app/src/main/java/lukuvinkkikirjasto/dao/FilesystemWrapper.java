package lukuvinkkikirjasto.dao;

import java.io.*;
import java.util.stream.*;

class FilesystemWrapper implements FilesystemProvider {
    public String read(String path) {
        try {
            return new BufferedReader(new FileReader(path)).lines().collect(Collectors.joining(""));
        } catch(Exception e) { }
        return "";
    }

    @Override
    public void write(String path, String data) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(data);
            writer.close();
        } catch(Exception e) { }
    }
}