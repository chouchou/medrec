package client;

import java.io.*;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLClient {


public SSLClient() {
	SSLSocketFactory  sslfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	try {
		SSLSocket sslsocket = (SSLSocket) sslfactory.createSocket("localhost", 9999);
		
		InputStream input = System.in;
		InputStreamReader inputstreamreader = new InputStreamReader(input);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

        OutputStream outputstream = sslsocket.getOutputStream();
        OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
        BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

        String string = null;
        while ((string = bufferedreader.readLine()) != null) {
            bufferedwriter.write(string + '\n');
            bufferedwriter.flush();
        }
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
}