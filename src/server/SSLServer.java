package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.*;

public class SSLServer {
	ConnectionHandler handler = new ConnectionHandler();

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

	private void setUpSomeTrustAndListen(int port)
			throws GeneralSecurityException, IOException {

		char[] password = "storepass".toCharArray();
		KeyStore clientTrusted = KeyStore.getInstance("JKS");
		clientTrusted.load(new FileInputStream("servertrust"), password);
		KeyStore serverKeys = KeyStore.getInstance("JKS");
		serverKeys.load(new FileInputStream("serverkeys"), password);

		KeyManagerFactory serverkmf = KeyManagerFactory.getInstance("SunX509");
		serverkmf.init(serverKeys, "keypass".toCharArray());
		TrustManagerFactory servertmf = TrustManagerFactory
				.getInstance("SunX509");
		servertmf.init(clientTrusted);

		SSLContext sslc = SSLContext.getInstance("TLS");
		sslc.init(serverkmf.getKeyManagers(), servertmf.getTrustManagers(),
				null);
		ServerSocketFactory factory = sslc.getServerSocketFactory();

		SSLServerSocket ss = (SSLServerSocket) factory.createServerSocket(port);
		ss.setNeedClientAuth(true);
		System.out.println("Made it");
		SSLSocket connection = (SSLSocket) ss.accept();
		SSLSession session = connection.getSession();

		BufferedReader r = new BufferedReader(new InputStreamReader(connection
				.getInputStream()));
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(connection
				.getOutputStream()));

		javax.security.cert.X509Certificate cert = session
				.getPeerCertificateChain()[0];
		String subject = cert.getSubjectDN().getName();

		if (handler.validateSubject(subject)) {
			handler.startCommunication(w, r);
		} else {
			w.write("Subject not in the system");
			w.flush();
			// Close socket
		}

	}

}
