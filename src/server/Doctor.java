package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Doctor extends Human {
	private HashMap<Integer, Human> accessToThesePpl;
	public Doctor(int personalNumber, HashMap<Integer, Human> accessToThesePpl) {
		super(personalNumber, accessToThesePpl);
	}
	public void createRecord(int id, String fileName) throws IOException{
		
		Human target =accessToThesePpl.get(id);
		if(target != null){
		File file = new File(target.getPath()+fileName+".txt");
		}
	}

	}


