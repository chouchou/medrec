package server;

import java.util.HashMap;

public class HumanFactory {

	public HumanFactory() {
		
	}
public Human createHumam(int personalNumber, HashMap<Integer, Human> accessToThesePpl, String role){
	if(role.equals("Patient")){
		return new Patient(personalNumber, accessToThesePpl);
	}else if(role.equals("Nurse")){
		return new Nurse(personalNumber, accessToThesePpl);
	}else if(role.equals("Doctor")){
		return new Doctor(personalNumber, accessToThesePpl);
	}else if(role.equals("SS")){
		return new SocialService(personalNumber, accessToThesePpl);
	}
	return null;
}
}
