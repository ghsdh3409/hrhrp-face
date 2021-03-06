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

	public ArrayList<Person> recognize(Group group) throws FaceppParseException, JSONException {

		ArrayList<Person> recogPersons = new ArrayList<Person>();

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

			if (candidate == null || candidate.getConfidence() < THRESHOLD) {
				Person person = new Person();
				person.addFace(face);
				person.setGroup(group);
				person.create();
				person.setIsAutoDetected(false);
				recogPersons.add(person);
			} else {
				String personId = candidate.getPersonId();
				Person person = new Person(personId, KEY_PERSON_ID);
				person.updateAddedFace(face);
				Log.log(DEBUG_MODE, person.getPersonName());
				person.setIsAutoDetected(true);
				recogPersons.add(person);
			}	
		}

		return recogPersons;
	}

	public void train(Group group) throws FaceppParseException {
		Log.log(DEBUG_MODE, httpRequests.trainIdentify(new PostParameters().setGroupName(group.getGroupName())).toString());	
	}

	public void setImageUrl(String aImageUrl) {
		imageUrl = aImageUrl;
	}

}
