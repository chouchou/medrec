package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class DataParser {
	private String fileName;
	private Human currentUser;
	private Scanner scan;

	public DataParser(String fileName) {
		this.fileName = fileName;
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean identifyUser(String subject) throws Exception {
		reloadFile();
		String strLine;
			String s2 = subject.substring(3);
			String[] number = s2.split(",");
			s2 = number[0];
			while (scan.hasNextLine()) {
				strLine = scan.nextLine();
				String[] s1 = strLine.split(":");
								
				if(s1[0].equals(s2)){
					loadUser(s1);
					return true;
	
			}
			}
	
		return false;
	}
		public HashMap<String, Human> findPatients(int division) throws Exception {
			reloadFile();
			HashMap<String, Human> patients = new HashMap<String, Human>();

				String strLine;
				while (scan.hasNextLine()) {
					strLine = scan.nextLine();
					String[] s = strLine.split(":");
					if(s[1].equals("Patient")&&Integer.parseInt(s[2])==division){
						patients.put(s[0], new Patient(s[0],new HashMap<String,Human>()));
					}
					
				}
			
		return patients;
		
	}
		
		public Human getCurrentUser(){
			return currentUser; 
			
		}

		public void loadUser(String[] s) throws Exception{
			HumanFactory hf = new HumanFactory();
			HashMap<String, Human> write = new HashMap<String,Human>(); 
			if(s[1].equalsIgnoreCase("SS")){
					addAllRead(s[0]);
			}else if (!s[1].equals("Patient")){
				HashMap<String, Human> read = findPatients(Integer.parseInt(s[2]));
				for(int i=3;i<s.length;i++){
					write.put(s[i], new Patient(s[i], new HashMap<String,Human>()));
					read.put(s[i], new Patient(s[i], new HashMap<String,Human>()));
				}
				currentUser = hf.createHumam(s[0], read, write, s[1]);
				
			}else{
				currentUser = hf.createHumam(s[0], new HashMap<String,Human>(), null, s[1]);
			}
			
		}
		private void addAllRead(String s2){
		reloadFile();
		HashMap<String, Human> read = new HashMap<String,Human>(); 
		
		String strLine;
			while (scan.hasNextLine()) {
				
				strLine = scan.nextLine();
				String[] s = strLine.split(":");
				read.put(s[0], new Patient(s[0], new HashMap<String,Human>()));
			
		}
			currentUser = new SocialService(s2, read);
		}
		private void reloadFile(){
			scan.close();
			try {
				scan = new Scanner(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	
}

