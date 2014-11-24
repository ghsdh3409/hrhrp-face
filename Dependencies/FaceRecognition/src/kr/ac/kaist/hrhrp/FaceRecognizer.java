package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.type.Face;
import kr.ac.kaist.hrhrp.type.Group;
import kr.ac.kaist.hrhrp.type.Init;
import kr.ac.kaist.hrhrp.type.Person;
import kr.ac.kaist.hrhrp.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class FaceRecognizer extends Init {
	
	private String imageUrl;

	public FaceRecognizer() {
	
	}
	
	public ArrayList<ArrayList<Person>> recognize(Group group) throws FaceppParseException, JSONException {
		
		ArrayList<Person> recogPersons = new ArrayList<Person>();
		ArrayList<Person> newPersons = new ArrayList<Person>();
		
		ArrayList<ArrayList<Person>> recogResults = new ArrayList<ArrayList<Person>>();
				
		JSONObject recogResult = httpRequests.recognitionIdentify(new PostParameters().setGroupName(group.getGroupName()).setUrl(imageUrl));
		Log.log(DEBUG_MODE, recogResult.toString());
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
				Person person = new Person(personId, KEY_PERSON_ID);
				person.addFace(face);
				Log.log(DEBUG_MODE, person.getPersonName());
				
				recogPersons.add(person);
				
			} else {
				// TO DO : ADD Face to new person
				Person person = new Person();
				person.setFace(face);
				person.setGroup(group);
				person.create();
				
				newPersons.add(person);
			}			
		}
		
		recogResults.add(recogPersons);
		recogResults.add(newPersons);
		
		return recogResults;
	}

	public void train(Group group) throws FaceppParseException {
		Log.log(DEBUG_MODE, httpRequests.trainIdentify(new PostParameters().setGroupName(group.getGroupName())).toString());	
	}
	
	public void setImageUrl(String aImageUrl) {
		imageUrl = aImageUrl;
	}

}
