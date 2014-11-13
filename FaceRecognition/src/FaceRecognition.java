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
	
	public static void main(String[] args) {
		String groupName = "DaehoonKim_Test";
		ArrayList<String> imageUrls = new ArrayList<String>();
		
		Info info = new Info();
		
		imageUrls.add("http://itsue.kr/data/cheditor4/1404/f0fdb4c3f58e3e3f8e77162d893d3055_Qhqz4QJJ6mx4I.jpg");
		try {
			recognition(imageUrls, groupName);
			for (Person person : info.getNewPersons()) {
				Log.log(DEBUG_MODE, person.getPersonName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
