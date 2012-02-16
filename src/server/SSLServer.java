package server;

import java.io.IOException;
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
	
	public void listen(int port) throws IOException{
		ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
		 
		
		SSLServerSocket	ss = (SSLServerSocket) factory.createServerSocket(port);
		SSLSocket connection = (SSLSocket)ss.accept();
		SSLSession session = connection.getSession();
		javax.security.cert.X509Certificate cert = session.getPeerCertificateChain()[0];
		cert.getSerialNumber();
		
		
	}

}
