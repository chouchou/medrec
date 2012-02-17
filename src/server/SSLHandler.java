package server;

import java.util.HashMap;

public class SSLHandler {
	private DataParser parser;
	public SSLHandler(){
		parser = new DataParser();
	}
	
	public boolean validateSubject(String subject){
		
		return parser.parse("txtfile");
		
	}
	
	public Human isInSystem(){
		return parser.currentPerson();
	}

}
