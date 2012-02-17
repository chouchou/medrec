package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public abstract class Human {
	private int pNbr;
	private HashMap<Integer, Human> read;
	public Human(int pNbr, HashMap<Integer, Human> read){
	this.read = read;
	this.pNbr = pNbr;
	read.put(pNbr,this);
	}
	
	
	
	public String getPath(){
		return pNbr + "\\";
	}

	public String readRecord(int id, String fileName) throws FileNotFoundException{
		StringBuilder sb = new StringBuilder();
		
		
		if(checkAccess(id)){
			FileReader f = new FileReader(read.get(id).getPath()+fileName);
			return f.toString();
		}
		return "Couldn't compute";
	}
	
	public String writeRecord(int id,String fileName,String message){
		return"No privilegies";
	}

public boolean checkAccess(int id){
	if(read.get(id) != null){
		return true;
	}else{
		return false;
	}
}
}