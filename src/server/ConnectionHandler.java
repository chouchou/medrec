package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConnectionHandler {
	private DataParser parser;
	private static final char ADD = 'A';
	private static final char VIEW = 'W';
	private static final char REMOVE = 'R';
	
	public ConnectionHandler(){
		parser = new DataParser();
	}
	
	public boolean validateSubject(String subject){
		
		return parser.parse("txtfile");
		
	}
	
	public void enableCommunication(BufferedWriter writer, BufferedReader reader) throws IOException{
		try {
			writer.write("Communication is now open\n Use commands:\nA - For adding new entry\nV personNumber - For viewing entry with pnbr\n " +
					"R personNumber - For removing entry with pnbr\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char command = reader.readLine().toCharArray()[0];
		
		while(command != 0){
			
			switch(command){
			case ADD : writer.write("Go fuck yourself");
						writer.flush();
			break;
			default : writer.write("FEL FEL");
				break;
			}
			command = reader.readLine().toCharArray()[0];
		}
		
	}

}
