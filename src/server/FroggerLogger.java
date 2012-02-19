package server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FroggerLogger {  
    
    public static final Logger myLogger = Logger.getLogger("Logger");  
      
    private static FroggerLogger instance = null;  
         
       public static FroggerLogger getInstance() {  
          if(instance == null) {  
    prepareLogger();  
             instance = new FroggerLogger ();  
          }  
          return instance;  
       }  
       
    private static void prepareLogger() {  
    FileHandler myFileHandler = null;
	try {
		myFileHandler = new FileHandler("log.txt");
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
            myFileHandler.setFormatter(new SimpleFormatter());  
            myLogger.addHandler(myFileHandler);  
            myLogger.setUseParentHandlers(false);  
            myLogger.setLevel(Level.ALL);  
    }  
       
}