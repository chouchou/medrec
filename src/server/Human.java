package server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public abstract class Human {
	private String pNbr;
	protected HashMap<String, Human> read;

	public Human(String pNbr, HashMap<String, Human> read) {
		this.read = read;
		this.pNbr = pNbr;
		read.put(pNbr, this);
	}

	public String getPath() {
		return pNbr + "\\";
	}

	public String readRecord(String id, String fileName)
			throws FileNotFoundException {

		if (hasReadAccess(id)) {
			FileReader f = new FileReader(read.get(id).getPath() + fileName);
			return f.toString() + "\n";
		}
		return "Not possible to read File: " + read.get(id).getPath()
				+ fileName + "\n";
	}

	public abstract String writeRecord(String id, String fileName,
			String message);

	public abstract String removeRecord(String id, String fileName);

	public abstract String createRecord(String id, String fileName);

	public boolean hasReadAccess(String id) {
		if (read.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}
}