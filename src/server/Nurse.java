package server;

import java.util.HashMap;

public class Nurse extends MedicalPersonal {

	public Nurse(String personalNumber, HashMap<String, Human> read,
			HashMap<String, Human> write) {
		super(personalNumber, read, write);
	}

	@Override
	public String createRecord(String id, String fileName) {

		return "No access\n";
	}

}
