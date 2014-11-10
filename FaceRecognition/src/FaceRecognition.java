import java.util.ArrayList;

import org.json.JSONException;

import com.facepp.error.FaceppParseException;

import kr.ac.kaist.hrhrp.FaceRecognizer;
import kr.ac.kaist.hrhrp.type.Group;

public class FaceRecognition {
	
	public void recognition(ArrayList<String> imageUrls, String groupName) throws FaceppParseException, JSONException {
		Group group = new Group(groupName);
		FaceRecognizer recognizer = new FaceRecognizer();
		for (String imageUrl : imageUrls) {
			recognizer.setImageUrl(imageUrl);
			recognizer.recognize(group);
		}
		recognizer.train(group);
	}
	
	public static void main(String[] args) {
	
	}
}
