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
	
	public static void recognition(ArrayList<String> imageUrls, String groupName) throws FaceppParseException, JSONException {
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		for (String imageUrl : imageUrls) {
			recognizer.setImageUrl(imageUrl);
			recognizer.recognize(group);
		}
		recognizer.train(group);
	}
	
	public static void personUpdate(Person person, String personName, String groupName) throws FaceppParseException, JSONException {
		Log.log(DEBUG_MODE, person.getPersonName());
		person.setPersonName(personName);
		person.update();
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		recognizer.train(group);
	}
	
	public static void main(String[] args) {
		String groupName = "DaehoonKim_Test";
		ArrayList<String> imageUrls = new ArrayList<String>();
		
		Info info = new Info();
		
		imageUrls.add("http://img.tvreport.co.kr/images/20130807/20130807_1375853314_11589400_1.jpg");
		try {
			recognition(imageUrls, groupName);
			for (Person person : info.getNewPersons()) {
				String newPersonName = "Soeun Kim";
				personUpdate(person, newPersonName, groupName);
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
