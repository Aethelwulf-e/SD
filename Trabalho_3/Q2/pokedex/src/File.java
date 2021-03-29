import java.io.*;

public class File {
	
	File(String caminho) {
		this.caminho = caminho;
		try {
			out = new FileWriter(this.caminho);
			pWriter = new PrintWriter(out);
		} catch (IOException e) {
			System.out.println("IO error: " + e.getMessage());
		}
	}
	
	private String caminho;
	private FileWriter out;
	private PrintWriter pWriter;
	
	public void write(String texto) {
		pWriter.println(texto);
		pWriter.close();
	}
}
