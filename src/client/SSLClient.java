package client;

import java.io.*;
import java.net.UnknownHostException;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
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

private void establishTrustWithServer()throws Exception{
	
	char[] password = "storepass".toCharArray();
	KeyStore serverTrusted = KeyStore.getInstance("JKS");
	serverTrusted.load(new FileInputStream("serverpublic"),password);
	KeyStore clientKeys = KeyStore.getInstance("JKS");
	clientKeys.load(new FileInputStream("clientkeystore"),password);
	
	KeyManagerFactory clientkmf = KeyManagerFactory.getInstance("SunX509");
	clientkmf.init(clientKeys, "keypass".toCharArray());
	TrustManagerFactory clienttmf = TrustManagerFactory.getInstance("SunX509");
	clienttmf.init(serverTrusted);
	
	SSLContext sslc = SSLContext.getInstance("TLS");
	sslc.init(clientkmf.getKeyManagers(), clienttmf.getTrustManagers(), null);
	SSLSocketFactory factory = sslc.getSocketFactory();
	
	SSLSocket ss = (SSLSocket) factory.createSocket("localhost",9999);
	BufferedWriter w = new BufferedWriter(new OutputStreamWriter(ss.getOutputStream()));
    BufferedReader r = new BufferedReader(new InputStreamReader(ss.getInputStream()));
    ss.startHandshake();
	w.write("hej");
}
}