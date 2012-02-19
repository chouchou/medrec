package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

public class ConnectionHandler {
	private DataParser parser;
	private static final char ADD = 'A';
	private static final char VIEW = 'V';
	private static final char REMOVE = 'R';
	private static final char EXIT = 'E';
	private static final char WRITE = 'W';
	private String response;
	private String fileName; 
	public ConnectionHandler() {
		parser = new DataParser("Users.txt");
	}

	public boolean validateSubject(String subject) {

		try {
			return parser.identifyUser(subject);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return false;

	}

	private void enableCommunication(BufferedWriter writer,
			BufferedReader reader, String id) throws IOException {
		try {
			writer.write("Communication is now open Use commands: A - "
					+ "For adding new entry V personNumber - "
					+ "For viewing entry with pnbr R personNumber - "
					+ "For removing entry with pnbr\n"
					+ "W - For writing to entyr\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		char command = reader.readLine().toCharArray()[0];

		while (command != 0) {

			switch (command) {
			case ADD:
				writer.write("Specify");
				writer.write("Adding journal to patient\n");
				break;
			case WRITE:
				writer.write("Specify filename\n");
				writer.flush();
				fileName = reader.readLine();
				writer.write("Write message\n");
				writer.flush();
				String message = reader.readLine();
				String whatHappend = getCurrentUser().writeRecord(
						id, fileName, message);
				writer.write(whatHappend);
				writer.flush();
			case VIEW:
				writer.write("Specify filename\n");
				writer.flush();
				fileName = reader.readLine();
				getCurrentUser().readRecord(id, fileName);
				break;
			case REMOVE:
				break;
			case EXIT:
				startCommunication(writer, reader);
			default:
				writer.write("Wrong Format, try again\n");
				break;

			}
			writer.flush();
			command = reader.readLine().toCharArray()[0];
		}

	}

	private Human getCurrentUser() {
		return parser.getCurrentUser();
	}

	public void startCommunication(BufferedWriter writer, BufferedReader reader)
			throws IOException {
		writer
				.write("Please specify a patient with format YYMMDD or EXIT to close application\n");
		writer.flush();
		response = reader.readLine();
		if (response == "EXIT") {
			// Close system
		}

		else if (getCurrentUser().checkAccess(response)) {
			writer.write("Access granted for patient: " + response);
			writer.flush();
			enableCommunication(writer, reader, response);
		} else {
			writer.write("Unable to perform operation");
			writer.flush();
			// Close system
		}

	}

}
