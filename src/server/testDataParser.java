package server;

public class testDataParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fakeSubject = "CN=5006121516, OU=, O=Patient, L=Lund, ST=SKåne, C=SE";
		DataParser dp = new DataParser("Users");
		dp.identifyUser(fakeSubject);
	}

}
