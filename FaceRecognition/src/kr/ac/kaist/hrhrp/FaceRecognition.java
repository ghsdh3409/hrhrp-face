import java.util.ArrayList;

import org.json.JSONException;

import com.facepp.error.FaceppParseException;

import kr.ac.kaist.hrhrp.FaceRecognizer;
import kr.ac.kaist.hrhrp.type.Group;
import kr.ac.kaist.hrhrp.type.Info;
import kr.ac.kaist.hrhrp.type.Init;
import kr.ac.kaist.hrhrp.type.Person;
import kr.ac.kaist.hrhrp.util.Log;

public class FaceRecognition extends Init {
	
	private String groupName;
	
	public void recognition(ArrayList<String> imageUrls) throws FaceppParseException, JSONException {
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		for (String imageUrl : imageUrls) {
			recognizer.setImageUrl(imageUrl);
			recognizer.recognize(group);
		}
		recognizer.train(group);
	}
	
	public ArrayList<Person> getNewPerson() {
		Info info = new Info();
		try {
			ArrayList<Person> newPersons = info.getNewPersons();
			return newPersons;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void personUpdate(Person person, String personName) throws FaceppParseException, JSONException {
		Log.log(DEBUG_MODE, person.getPersonName());
		person.setPersonName(personName);
		person.update();
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		recognizer.train(group);
	}
	
	public FaceRecognition(String aGroupName) {
		groupName = aGroupName;
	}
	
	public FaceRecognition() {
		groupName = "DaehoonKim_Test";
	}
	
	/*	
	public void main(String[] args) {
		String groupName = "DaehoonKim_Test";
		ArrayList<String> imageUrls = new ArrayList<String>();
		
		Info info = new Info();
		
		imageUrls.add("http://img.tvreport.co.kr/images/20130807/20130807_1375853314_11589400_1.jpg");
		try {
			recognition(imageUrls);
			for (Person person : info.getNewPersons()) {
				String newPersonName = "Soeun Kim";
				personUpdate(person, newPersonName, groupName);
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
