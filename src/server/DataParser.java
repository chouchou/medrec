package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class DataParser {
	private String fileName;
	private Human currentUser;
	FileInputStream fstream;
	DataInputStream in;
	BufferedReader br;

	public DataParser(String fileName) {
		this.fileName = fileName;
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
	}

	public boolean identifyUser(String subject) throws Exception {
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] s1 = strLine.split(":");
				String s2 = subject.substring(3, 13);
								
				if(s1[0].equals(s2)){
					loadUser(s1);
					return true;
	
			}
			}
	
		return false;
	}
		public HashMap<Integer, Human> findPatients(int division) throws Exception {
			HashMap<Integer, Human> patients = new HashMap<Integer, Human>();

				String strLine;
				while ((strLine = br.readLine()) != null) {
					String[] s = strLine.split(":");
					if(s[1]=="Patient"&&Integer.parseInt(s[2])==division){
						patients.put(Integer.parseInt(s[0]), new Patient(Integer.parseInt(s[0]),new HashMap<Integer,Human>()));
					}
					
				}
			
		return patients;
		
	}
		
		public Human getCurrentUser(){
			return currentUser; 
			
		}

		public void loadUser(String[] s) throws Exception{
			HumanFactory hf = new HumanFactory();
			HashMap<Integer, Human> write = new HashMap<Integer,Human>(); 
			if(s[1]=="SS"){
					
			}else if (s[1]!="Patient"){
				HashMap<Integer, Human> read = findPatients(Integer.parseInt(s[2]));
				String[] additPat = s[3].split(",");
				for(int i=0;i<additPat.length;i++){
					write.put(Integer.parseInt(additPat[0]), new Patient(Integer.parseInt(additPat[0]), new HashMap<Integer,Human>()));
				}
				currentUser = hf.createHumam(Integer.parseInt(s[0]), read, write, s[1]);
				
			}else{
				currentUser = hf.createHumam(Integer.parseInt(s[0]), new HashMap<Integer,Human>(), null, s[1]);
			}
			
		}
		
	}


