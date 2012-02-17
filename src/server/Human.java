package server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public abstract class Human {
	private int pNbr;
	private HashMap<Integer, Human> access;
	public Human(int pNbr, HashMap<Integer, Human> access){
	this.access = access;
	this.pNbr = pNbr;
	access.put(pNbr,this);
	}
	
	
	
	public String getPath(){
		return pNbr + "\\";
	}

	public String writeRecord(int id,String fileName,String message) throws IOException{
		
		
		Human target =access.get(id);
		FileWriter fstream = new FileWriter(target.getPath()+fileName+".txt");
		fstream.write(message);
		return "Successfully writen to: " + target.getPath()+fileName;
		
		
		
	}

public boolean checkAccess(int id){
	if(access.get(id) != null){
		return true;
	}else{
		return false;
	}
}
}