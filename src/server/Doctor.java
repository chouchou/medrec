package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

public class Doctor extends MedicalPersonal {

	public Doctor(String personalNumber, HashMap<String, Human> read,
			HashMap<String, Human> write) {
		super(personalNumber, read, write);

	}

	public String createRecord(String id, String fileName) {

		if (hasWriteAccess(id)) {

			File file = new File(write.get(id).getPath() + fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.myLogger.log(Level.INFO, pNbr + " created record "
					+ fileName + "for user " + id);
			return "File: " + fileName + " was added\n";

		}
		return "File: " + fileName + " was not added\n";

	}

	public void associateNurse(String nurseId, String patientId) {
		StringBuilder sb = new StringBuilder();
		Scanner scan = new Scanner("Users");
		Scanner temp = new Scanner("Users");
		String[] line;
		boolean found = false;

		while (scan.hasNextLine()) {
			line = scan.nextLine().split(":");
			sb.append(temp.nextLine() + "\n");
			if (line[0].equals(nurseId) && line[1].equals("Nurse")) {
				sb.append(temp.nextLine() + ":" + patientId + "\n");
				found = true;
			}

		}
		if (found) {
			FileWriter fstream = null;
			BufferedWriter bf = null;
			try {
				fstream = new FileWriter("Users");
				bf = new BufferedWriter(fstream);
				bf.write(sb.toString());
				bf.close();
				logger.myLogger.log(Level.INFO, pNbr + " Associated Nurse : "
						+ nurseId + "to patient: " + patientId
						+ "Made changes in file: User");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		scan.close();
		temp.close();

	}
}
