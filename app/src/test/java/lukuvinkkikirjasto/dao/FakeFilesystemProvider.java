package lukuvinkkikirjasto.dao;

class FakeFilesystemProvider implements FilesystemProvider {
    String content = "";

    public String read(String path) {
        return content;
    }

    public void write(String path, String data) {
        content = data;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }
}