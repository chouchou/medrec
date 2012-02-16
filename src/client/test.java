package client;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "Clientkeystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "storepass");
		System.setProperty("javax.net.ssl.trustStore", "Clienttruststore");
		System.setProperty("javax.net.ssl.truststorPassword", "storepass");
		SSLClient c = new SSLClient();

	}

}
