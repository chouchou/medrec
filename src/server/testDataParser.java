package server;

public class testDataParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fakeSubject = "CN=110, OU=, O=Patient, L=Lund, ST=SKåne, C=SE";
		DataParser dp = new DataParser("Users");
		try {
			dp.identifyUser(fakeSubject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
