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
	

	public DataParser(String fileName) {
		this.fileName = fileName;
	}

	public boolean identifyUser(String subject) {
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] s1 = strLine.split(":");
				String s2 = subject.substring(3, 13);
								
				if(s1[0].equals(s2)){
					loadUser(s1);
					return true;
					
				}
				
			}
			
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return false;
	}
		public HashMap<Integer, Human> findPatients(int division) throws Exception {
			HashMap<Integer, Human> patients = new HashMap<Integer, Human>();
			
				
				FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
			HashMap<Integer, Human> complete = new HashMap<Integer,Human>(); 
			if(s[1]=="SS"){
					
			}else if (s[1]!="Patient"){
				complete.putAll(findPatients(Integer.parseInt(s[2])));
				String[] additPat = s[3].split(",");
				for(int i=0;i<additPat.length;i++){
					complete.put(Integer.parseInt(additPat[0]), new Patient(Integer.parseInt(additPat[0]), new HashMap<Integer,Human>()));
				}
				currentUser = hf.createHumam(Integer.parseInt(s[0]), complete, s[1]);
				
			}else{
				complete.put(Integer.parseInt(s[0]), new Patient(Integer.parseInt(s[0]), new HashMap<Integer,Human>()));
				currentUser = hf.createHumam(Integer.parseInt(s[0]), complete, s[1]);
			}
			
		}
		
	}


