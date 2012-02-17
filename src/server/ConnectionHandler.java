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
	private static final char EXIT = 'E';
	private String response;
	public ConnectionHandler(){
		parser = new DataParser();
	}
	
	public boolean validateSubject(String subject){
		
		return parser.parse("txtfile");
		
	}
	
	private void enableCommunication(BufferedWriter writer, BufferedReader reader) throws IOException{
		try {
			writer.write("Communication is now open\n Use commands:\nA - For adding new entry\nV personNumber - For viewing entry with pnbr\n " +
					"R personNumber - For removing entry with pnbr\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		char command = reader.readLine().toCharArray()[0];
		
		while(command != 0){
			
			switch(command){
			case ADD : 
				writer.write("Adding journal to patient");	
			break;
			case VIEW : break;
			case REMOVE : break;
			case EXIT : startCommunication(writer, reader);
			default : writer.write("Wrong Format, try again\n");
			break;
				
			}
			writer.flush();
			command = reader.readLine().toCharArray()[0];
		}
		
	}
	
	private Human getCurrentUser(){
		return parser.currentPerson();
	}
	
	public void startCommunication(BufferedWriter writer, BufferedReader reader) throws IOException{
		writer.write("Please specify a patient with format YYMMDD or EXIT to close application");
		writer.flush();
		response = reader.readLine();
		if(response == "EXIT"){
			//Close system
		}
		getCurrentUser();
		else if(getCurrentUser().viewJournal(response)){
			writer.write("Access granted for patient: " + response);
			writer.flush();
			enableCommunication(writer, reader);
		}
		else{
			writer.write("Unable to perform operation");
			writer.flush();
			//Close system
		}
		
		
		
		
	}

}
