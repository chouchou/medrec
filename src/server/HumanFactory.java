package server;

import java.util.HashMap;

public class HumanFactory {

	public HumanFactory() {
		
	}
public Human createHumam(int personalNumber, HashMap<Integer, Human> read, HashMap<Integer, Human> write , String role){
	if(role.equals("Patient")){
		return new Patient(personalNumber, read);
	}else if(role.equals("Nurse")){
		return new Nurse(personalNumber, read, write);
	}else if(role.equals("Doctor")){
		return new Doctor(personalNumber, read, write);
	}else if(role.equals("SS")){
		return new SocialService(personalNumber, read);
	}
	return null;
}
}
