package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.type.Face;
import kr.ac.kaist.hrhrp.type.Group;
import kr.ac.kaist.hrhrp.type.Init;
import kr.ac.kaist.hrhrp.type.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class FaceRecognizer extends Init {
	
	private String imageUrl;

	public FaceRecognizer() {
	
	}
	
	public void recognize(Group group) throws FaceppParseException, JSONException {
		
		JSONObject recogResult = httpRequests.recognitionIdentify(new PostParameters().setGroupName(group.getGroupName()).setUrl(imageUrl));
		JSONArray faceResult = recogResult.getJSONArray("face");
		
		ArrayList<Face> faces = new ArrayList<Face>();
		
		for (int i=0; i<faceResult.length(); i++) {
			Face face = new Face(faceResult.getJSONObject(i));
			faces.add(face);
		}
		
		for(Face face : faces) {
			
			Face.Candidate candidate = face.getCandidate();
			Double confidence = candidate.getConfidence();
			String personId = candidate.getPersonId();
			
			if (confidence > THRESHOLD) {
				Person person = new Person(personId);
				person.addFace(face);
			} else {
				// TO DO : ADD Face to new person
				Person person = new Person();
				person.setFace(face);
				person.setGroup(group);
				person.create();
			}			
		}		
	}

	public void train(Group group) throws FaceppParseException {
		httpRequests.trainIdentify(new PostParameters().setGroupName(group.getGroupName()));	
	}
	
	public void setImageUrl(String aImageUrl) {
		imageUrl = aImageUrl;
	}

}
