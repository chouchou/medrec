package server;

import java.util.HashMap;

public class DataParser {
	
	private int currentId;
	private HashMap<Integer,Human> accessToThesePpl = new HashMap<Integer,Human>();
	public DataParser(){
		
	}
	
	public boolean parse(String textFile){
		return false;
	}
	
	public Human currentPerson(){
		return new Human(currentId,accessToThesePpl);
	}

}
