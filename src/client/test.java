package client;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl,handshake");
		SSLClient c = new SSLClient();

	}

}
