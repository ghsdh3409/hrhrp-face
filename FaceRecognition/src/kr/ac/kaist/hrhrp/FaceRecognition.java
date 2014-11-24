package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.type.Group;
import kr.ac.kaist.hrhrp.type.Info;
import kr.ac.kaist.hrhrp.type.Init;
import kr.ac.kaist.hrhrp.type.Person;
import kr.ac.kaist.hrhrp.util.Log;

public class FaceRecognition extends Init {

	private String groupName;

	public ArrayList<Person> recognition(String imageUrl) {
		ArrayList<Person> persons = new ArrayList<Person>();
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		recognizer.setImageUrl(imageUrl);
		try {
			persons = recognizer.recognize(group);
			recognizer.train(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}

	public ArrayList<Person> recognition(ArrayList<String> imageUrls) {
		ArrayList<Person> persons = new ArrayList<Person>();
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		for (String imageUrl : imageUrls) {
			recognizer.setImageUrl(imageUrl);
			try {
				persons = recognizer.recognize(group);
				recognizer.train(group);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return persons;
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

	public void personUpdate(Person person, String personName) {
		Log.log(DEBUG_MODE, person.getPersonName());
		person.setPersonName(personName);
		try {
			person.update();
			Group group = new Group(groupName);
			FaceRecognizer recognizer = new FaceRecognizer();
			recognizer.train(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FaceRecognition(String aGroupName) {
		groupName = aGroupName;
	}

	public FaceRecognition() {
		groupName = "DaehoonKim_Test";
	}
}
