package server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public abstract class MedicalPersonal extends Human {
	private HashMap<String, Human> read; 
	private HashMap<String, Human> write;
	public MedicalPersonal(String pNbr, HashMap<String, Human> read, HashMap<String, Human> write) {
		super(pNbr, read);
		this.write = write;
		
	}
public String writeRecord(String id,String fileName,String message) {
		
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
public boolean checkWritePermission(String id){
	if(write.get(id)!= null){
		return true;
	}
	else{
		return false;
	}
}

}
