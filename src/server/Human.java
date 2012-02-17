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

	public String writeRecord(int id,String fileName) throws IOException{
		
		if(checkAccess(id)){
	Human target =access.get(id);
		FileWriter fstream = new FileWriter(target.getPath()+fileName+".txt");
		fstream.write("");
		return "Successfully writen to: " + target.getPath()+fileName;
		
		}
		else{
			return "Access Denied";
		}
	}

public boolean checkAccess(int id){
	if(access.get(id) != null){
		return true;
	}else{
		return false;
	}
}
}