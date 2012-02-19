package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Doctor extends MedicalPersonal {

	public Doctor(String personalNumber, HashMap<String, Human> read,
			HashMap<String, Human> write) {
		super(personalNumber, read, write);

	}

	public String createRecord(String id, String fileName) {

		if (hasWriteAccess(id)) {

			File file = new File(write.get(id).getPath() + fileName + ".txt");
			return "File: " + fileName + " was added\n";
		}
		return "File: " + fileName + " was not added\n";

	}

	public void associateNurse(int nurseId) {

	}

}
