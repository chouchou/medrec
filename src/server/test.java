package server;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.keyStore", "Serverkeystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "storepass");
		System.setProperty("javax.net.ssl.trustStore", "Clientkeystore");
		System.setProperty("javax.net.ssl.truststorPassword", "storepass");
		SSLServer s = new SSLServer(9999);

	}

}
