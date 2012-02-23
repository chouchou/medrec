package client;

import java.io.*;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class SSLClient {

	public SSLClient(String fileName) {
		try {
			establishTrustWithServer(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void establishTrustWithServer(String fileName ) throws Exception {

		char[] password = "storepass".toCharArray();
		KeyStore serverTrusted = KeyStore.getInstance("JKS");
		serverTrusted.load(new FileInputStream("clienttrust"), password);
		KeyStore clientKeys = KeyStore.getInstance("JKS");
		clientKeys.load(new FileInputStream(fileName), password);

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
		

		BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(
				ss.getOutputStream()));
		
		String string = null;
		String s = "";
		System.out.println(inputbuffer.readLine());
		while ((string = bufferedreader.readLine()) != null) {
			bufferedwriter.write(string + '\n');
			bufferedwriter.flush();
			s = inputbuffer.readLine();
			if(s.equals("CLOSE")){
				bufferedreader.close();
				bufferedwriter.close();
				ss.close();
				return;
			}
			else{
			System.out.println(s);
			}
		}
	}

	private BufferedReader readFromKeyboard() {
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
		return bufferedreader;
	}
}
