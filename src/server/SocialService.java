package server;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public class SocialService extends Human {

	public SocialService(String personalNumber, HashMap<String, Human> read) {
		super(personalNumber, read);
	}

	public String removeRecord(String id, String fileName) {

		if (hasReadAccess(id)) {
			
			File file = new File(read.get(id).getPath() + fileName);
			file.delete();
			logger.myLogger.log(Level.INFO,"SocialServices deleted record "+fileName+" for user "+id);
			return "File: " + read.get(id).getPath() + fileName
					+ "Was succesfully deleted\n";

		}
		return "Couldn't delete File: " + read.get(id).getPath() + fileName
				+  "\n";
	}

	@Override
	public String writeRecord(String id, String fileName, String message) {

		return "No access\n";
	}

	@Override
	public String createRecord(String id, String fileName) {
		// TODO Auto-generated method stub
		return "No access\n";
	}

}
