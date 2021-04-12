package lukuvinkkikirjasto.dao;

interface FilesystemProvider {
	public String read(String path);
	public void write(String path, String content);
}