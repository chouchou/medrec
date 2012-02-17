package server;

import java.io.IOException;
import java.util.HashMap;

public class SocialService extends Human {

	public SocialService(int personalNumber,
			HashMap<Integer, Human> accessToThesePpl) {
		super(personalNumber, accessToThesePpl);
	}
	
	public String writeRecord(int id,String fileName) throws IOException{
		return "Access Denied";
	}

	public void Delete(int id){
		
	}
}
