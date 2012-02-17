package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Doctor extends Human {
	private HashMap<Integer, Human> accessToThesePpl;
	private HashMap<Integer, Human> write;
	public Doctor(int personalNumber, HashMap<Integer, Human> accessToThesePpl, HashMap<Integer, Human> write) {
		super(personalNumber, accessToThesePpl);
		this.write = write;
	}
	public void createRecord(int id, String fileName) throws IOException{
		
		Human target =accessToThesePpl.get(id);
		if(target != null){
		File file = new File(target.getPath()+fileName+".txt");
		}
	}
public String writeRecord(int id,String fileName,String message) {
		
		if(checkWritePermission(id)){
		Human target = write.get(id);
		FileWriter fstream = null;
		try {
			fstream = new FileWriter(target.getPath()+fileName+".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fstream.write(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Successfully writen to: " + target.getPath()+fileName;
		}
		return "Access Denied";
		
		
	}
public boolean checkWritePermission(int id){
	if(write.get(id)!= null){
		return true;
	}
	else{
		return false;
	}
}
	}


