package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public abstract class Human {
	private String pNbr;
	private HashMap<String, Human> read;
	public Human(String pNbr, HashMap<String, Human> read){
	this.read = read;
	this.pNbr = pNbr;
	read.put(pNbr,this);
	}
	
	
	
	public String getPath(){
		return pNbr + "\\";
	}

	public String readRecord(String id, String fileName) throws FileNotFoundException{
	
		
		
		if(checkAccess(id)){
			FileReader f = new FileReader(read.get(id).getPath()+fileName);
			return f.toString();
		}
		return "Couldn't compute";
	}
	
	public abstract String writeRecord(String id,String fileName,String message);

public boolean checkAccess(String id){
	if(read.get(id) != null){
		return true;
	}else{
		return false;
	}
}
}