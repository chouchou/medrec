package server;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("javax.net.debug", "ssl,handshake");
		SSLServer s = new SSLServer(9999);

	}

}
