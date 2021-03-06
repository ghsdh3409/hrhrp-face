package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.type.Face;
import kr.ac.kaist.hrhrp.type.Group;
import kr.ac.kaist.hrhrp.type.Info;
import kr.ac.kaist.hrhrp.type.Init;
import kr.ac.kaist.hrhrp.type.Person;
import kr.ac.kaist.hrhrp.util.Log;

public class FaceRecognition extends Init {

	private String groupName;

	public ArrayList<Person> recognition(String imageUrl) {
		ArrayList<Person> recogResults = new ArrayList<Person>();
		Group group = new Group(groupName);
		
		FaceRecognizer recognizer = new FaceRecognizer();
			
		try {
			recognizer.train(group);
			
			recognizer.setImageUrl(imageUrl);
			recogResults = recognizer.recognize(group);
			recognizer.train(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recogResults;
	}

	public ArrayList<Person> getNewPerson() {
		Info info = new Info();
		ArrayList<Person> newPersons = new ArrayList<Person>();
		try {
			newPersons = info.getNewPersons();
			return newPersons;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return newPersons;
		}
	}

	public Person personForTempUpdate(Person person, String personName) {
		Log.log(DEBUG_MODE, person.getPersonName());
		person.setPersonName(personName);
		try {
			person.updateTemp();
			Group group = new Group(groupName);
			FaceRecognizer recognizer = new FaceRecognizer();
			recognizer.train(group);
			return person;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Person personForAutoUpdate(String personId, String personName, Face face) {	
		try {
			Person person = new Person(personId, KEY_PERSON_ID);
			Log.log(DEBUG_MODE, person.getPersonName());
			person.setPersonName(personName);
			person.updateAuto(face);
			Group group = new Group(groupName);
			FaceRecognizer recognizer = new FaceRecognizer();
			recognizer.train(group);
			return person;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Person personForTempUpdate(String personId, String personName) {	
		try {
			Person person = new Person(personId, KEY_PERSON_ID);
			Log.log(DEBUG_MODE, person.getPersonName());
			person.setPersonName(personName);
			person.updateTemp();
			Group group = new Group(groupName);
			FaceRecognizer recognizer = new FaceRecognizer();
			recognizer.train(group);
			return person;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public FaceRecognition(String aGroupName) {
		groupName = aGroupName;
	}

	public FaceRecognition() {
		groupName = "HRHRP_Test";
	}
}
