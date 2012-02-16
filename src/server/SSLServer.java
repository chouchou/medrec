package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.cert.X509Certificate;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.*;

public class SSLServer {
	
	
	
	public SSLServer(int port){
		try {
			listen(port);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
	private void listen(int port) throws IOException{
		ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
		 
		
		SSLServerSocket	ss = (SSLServerSocket) factory.createServerSocket(port);
		ss.setNeedClientAuth(true);
		SSLSocket connection = (SSLSocket)ss.accept();
		System.out.println("connection established");
		establishAndValidate(connection);
		
		
		
	}
	
	private void establishAndValidate(SSLSocket connection) throws IOException{
		BufferedInputStream input = new BufferedInputStream(connection.getInputStream());
		BufferedOutputStream output = new BufferedOutputStream(connection.getOutputStream());
		SSLSession session = connection.getSession();
		javax.security.cert.X509Certificate cert = session.getPeerCertificateChain()[0];
		cert.getSerialNumber();
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		String s = "Hej Hej Hej";
		w.write(s,0,s.length());
		w.flush();
		
		
	}

}
