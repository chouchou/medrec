package server;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;

public class SSLServer {
	ConnectionHandler handler = new ConnectionHandler();
	FileHandler fileTxt;
	File log = new File("log.txt");
	Logger logger;

	public SSLServer(int port) {
		log.delete();
		try {
			fileTxt = new FileHandler("log.txt");
			logger = Logger.getLogger("MyLog");
		    logger.addHandler(fileTxt);
		    logger.setLevel(Level.ALL);
		    SimpleFormatter formatter = new SimpleFormatter();
		    fileTxt.setFormatter(formatter);

		      // the following statement is used to log any messages   
		      logger.log(Level.INFO,"Servern startas");

			
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
		
		SSLSocket connection = (SSLSocket) ss.accept();
		logger.log(Level.INFO,"User Conneting");
		SSLSession session = connection.getSession();

		BufferedReader r = new BufferedReader(new InputStreamReader(connection
				.getInputStream()));
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(connection
				.getOutputStream()));
		handler.startCommunication(w, r);
		javax.security.cert.X509Certificate cert = session
				.getPeerCertificateChain()[0];
		String subject = cert.getSubjectDN().getName();
		logger.log(Level.INFO,"Connected User:"+ subject);
//		if (handler.validateSubject(subject)) {
//			;
//		} else {
//			w.write("Subject not in the system");
//			w.flush();
//			// Close socket
//		}

	}

}
