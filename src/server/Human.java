package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class Human {
	protected String pNbr;
	protected HashMap<String, Human> read;
	File log = new File("log.txt");
	Logger logger;
	FileHandler fileTxt;

	public Human(String pNbr, HashMap<String, Human> read) {
		this.read = read;
		this.pNbr = pNbr;
		read.put(pNbr, this);
		try {
			fileTxt = new FileHandler("log.txt");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger = Logger.getLogger("MyLog");
	    logger.addHandler(fileTxt);
	    logger.setLevel(Level.ALL);
	    SimpleFormatter formatter = new SimpleFormatter();
	    fileTxt.setFormatter(formatter);
	}

	public String getPath() {
		return pNbr + "/";
	}

	public String readRecord(String id, String fileName)
			throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		
		if (hasReadAccess(id)) {
			FileReader fr = new FileReader(read.get(id).getPath()+fileName);
			BufferedReader br = new BufferedReader(fr);	
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(line != null){
				
				sb.append(line);
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			logger.log(Level.INFO, pNbr+" reads journal "+ fileName +" for user "+ id );
			return sb.toString()+"\n";
			
			
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
			logger.log(Level.INFO, pNbr+" tries to access "+ id+":Accepted");
			return true;
		} else {
			logger.log(Level.INFO, pNbr+" tries to access "+ id+":Denied");
			return false;
		}
	}
}