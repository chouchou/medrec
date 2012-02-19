package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Doctor extends MedicalPersonal {
	private HashMap<Integer, Human> read;
	private HashMap<Integer, Human> write;
	public Doctor(String personalNumber, HashMap<String, Human> read, HashMap<String, Human> write) {
		super(personalNumber, read, write);
		
	}
	
	public void createRecord(int id, String fileName) throws IOException{
		
		Human target =read.get(id);
		if(target != null){
		File file = new File(target.getPath()+fileName+".txt");
		}
	}
	public void associateNurse(int nurseId){
		
	}

	

	}


