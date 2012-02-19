package server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public abstract class MedicalPersonal extends Human {

	protected HashMap<String, Human> write;

	public MedicalPersonal(String pNbr, HashMap<String, Human> read,
			HashMap<String, Human> write) {
		super(pNbr, read);
		this.write = write;

	}

	public String writeRecord(String id, String fileName, String message) {

		if (hasWriteAccess(id)) {
			Human target = write.get(id);
			FileWriter fstream = null;
			try {
				fstream = new FileWriter(target.getPath() + fileName + ".txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fstream.write(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Successfully writen to: " + target.getPath() + fileName
					+ "\n";
		}
		return "Access Denied\n";

	}

	public String removeRecord(String id, String fileName) {
		return "No access\n";
	}

	public boolean hasWriteAccess(String id) {
		if (write.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}

}
