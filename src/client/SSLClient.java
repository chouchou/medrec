package client;

import java.io.*;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLClient {

	public SSLClient() {
		try {
			establishTrustWithServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void establishTrustWithServer() throws Exception {

		char[] password = "storepass".toCharArray();
		KeyStore serverTrusted = KeyStore.getInstance("JKS");
		serverTrusted.load(new FileInputStream("clienttrust"), password);
		KeyStore clientKeys = KeyStore.getInstance("JKS");
		clientKeys.load(new FileInputStream("clientkeys"), password);

		KeyManagerFactory clientkmf = KeyManagerFactory.getInstance("SunX509");
		clientkmf.init(clientKeys, "keypass".toCharArray());
		TrustManagerFactory clienttmf = TrustManagerFactory.getInstance("SunX509");
		clienttmf.init(serverTrusted);

		SSLContext sslc = SSLContext.getInstance("TLS");
		sslc.init(clientkmf.getKeyManagers(), clienttmf.getTrustManagers(),
				null);
		SSLSocketFactory factory = sslc.getSocketFactory();

		SSLSocket ss = (SSLSocket) factory.createSocket("localhost", 9999);
		ss.startHandshake();

		BufferedReader bufferedreader = readFromKeyboard();

		InputStream input = ss.getInputStream();
		InputStreamReader inputreader = new InputStreamReader(input);
		BufferedReader inputbuffer = new BufferedReader(inputreader);
		System.out.println(inputbuffer.readLine());
		System.out.println(inputbuffer.readLine());
		System.out.println(inputbuffer.readLine());
		System.out.println(inputbuffer.readLine());
		System.out.println(inputbuffer.readLine());
		
		OutputStream outputstream = ss.getOutputStream();
		OutputStreamWriter outputstreamwriter = new OutputStreamWriter(
				outputstream);

		BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
		String string = null;

		while ((string = bufferedreader.readLine()) != null) {
			bufferedwriter.write(string + '\n');
			bufferedwriter.flush();
			System.out.println(inputbuffer.readLine());
		}
	}

	private BufferedReader readFromKeyboard() {
		InputStream keyboard = System.in;
		InputStreamReader keyboardreader = new InputStreamReader(keyboard);
		BufferedReader bufferedreader = new BufferedReader(keyboardreader);
		return bufferedreader;
	}
}