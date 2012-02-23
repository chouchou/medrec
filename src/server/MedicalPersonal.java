package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

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
			BufferedWriter bf = null;
			try {
				fstream = new FileWriter(target.getPath() + fileName,true);
				bf = new BufferedWriter(fstream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bf.write(message);
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.myLogger.log(Level.INFO, pNbr+" wrote in record "+ fileName+"for user "+ id);
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
			logger.myLogger.log(Level.INFO, pNbr+" tries to write "+ id+" record :Accepted");
			return true;
		} else {
			logger.myLogger.log(Level.INFO, pNbr+" tries to write "+ id+" record :Denied");
			return false;
		}
	}

}
