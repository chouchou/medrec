package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.*;

public class SSLServer {

	public SSLServer(int port) {
		try {
			setUpSomeTrustAndListen(port);
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	private void establishAndValidate(SSLSocket connection) throws IOException {
//		BufferedInputStream input = new BufferedInputStream(connection
//				.getInputStream());
//		BufferedOutputStream output = new BufferedOutputStream(connection
//				.getOutputStream());
//		SSLSession session = connection.getSession();
//		// javax.security.cert.X509Certificate cert =
//		// session.getPeerCertificateChain()[1];
//		// cert.getSerialNumber();
//		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(connection
//				.getOutputStream()));
//		String s = "Hej Hej Hej";
//		w.write(s, 0, s.length());
//		w.flush();

	}

	private void setUpSomeTrustAndListen(int port) throws GeneralSecurityException,
			IOException {
		
		char[] password = "storepass".toCharArray();
		KeyStore clientTrusted = KeyStore.getInstance("JKS");
		clientTrusted.load(new FileInputStream("clientpublic"),password);
		KeyStore serverKeys = KeyStore.getInstance("JKS");
		serverKeys.load(new FileInputStream("serverkeystore"),password);
		
		KeyManagerFactory serverkmf = KeyManagerFactory.getInstance("SunX509");
		serverkmf.init(serverKeys, "keypass".toCharArray());
		TrustManagerFactory servertmf = TrustManagerFactory.getInstance("SunX509");
		servertmf.init(clientTrusted);
		
		SSLContext sslc = SSLContext.getInstance("TLS");
		sslc.init(serverkmf.getKeyManagers(), servertmf.getTrustManagers(), null);
		ServerSocketFactory factory = sslc.getServerSocketFactory();

		SSLServerSocket ss = (SSLServerSocket) factory.createServerSocket(port);
		ss.setNeedClientAuth(true);
		System.out.println("Made it");
		SSLSocket connection = (SSLSocket) ss.accept();
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		System.out.println("Made it More");
		SSLSession session = connection.getSession();
		javax.security.cert.X509Certificate cert = session.getPeerCertificateChain()[0];
		String subject = cert.getSubjectDN().getName();
		System.out.println (subject);
		

	
		
		 
	}

	

}
