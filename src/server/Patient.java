package server;

import java.util.HashMap;

public class Patient extends Human {

	public Patient(String personalNumber, HashMap<String, Human> read) {
		super(personalNumber, read);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String writeRecord(String id, String fileName, String message) {
		
		return "No privilegies";
	}

}
