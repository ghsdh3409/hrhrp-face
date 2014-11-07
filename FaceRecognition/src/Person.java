import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class Person extends Init {
	
	private String personId;
	private String personName;
	private String tag;
	
	private ArrayList<Group> groups = new ArrayList<Group>();
	
	private ArrayList<Face> faces = new ArrayList<Face>();
	
	public Person(String aPersonId) throws FaceppParseException, JSONException {
		setInfo(getInfo(aPersonId));
	}
	
	public Person(JSONObject personResult) throws FaceppParseException, JSONException {
		setInfo(personResult);
	}
	
	public void addFace(Face face) throws FaceppParseException {
		String faceId = face.faceId;
		httpRequests.personAddFace(new PostParameters().setPersonId(personId).setFaceId(faceId));
	}
	
	public void create() {
		PostParameters params = new PostParameters();
		params.setPersonName(personName);
		params.setTag(tag);
		ArrayList<String> faceIds = new ArrayList<String>();
		for (int i=0; i<faces.size(); i++) {
			Face face = faces.get(i);
			faceIds.add(face.getFaceId());
		}
		params.setFaceId(faceIds);
		
		ArrayList<String> groupIds = new ArrayList<String>();
		for (int i=0; i<groups.size(); i++) {
			Group group = groups.get(i);
			groupIds.add(group.getGroupId());
		}
		params.setFaceId(faceIds);	
	}
	
	public void setPersonId(String aPersonId) {
		//TO DO
	}
	
	public void setPersonName(String aPersonName) {
		//TO DO
	}
	
	public void setTag(String aTag) {
		//TO DO
	}
	
	public void setGroups(ArrayList<Group> aGroups) {
		//TO DO
	}
	
	public void setFaces(ArrayList<Face> aFaces) {
		//TO DO
	}
	
	public String getPersonId() {
		return personId;
	}
	
	public String getPersonName() {
		return personName;
	}
	
	public String getTag() {
		return tag;
	}
	
	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public ArrayList<Face> getFaces() {
		return faces;
	}
	
	private JSONObject getInfo(String personId) throws FaceppParseException, JSONException {
		JSONObject personResult = httpRequests.personGetInfo(new PostParameters().setPersonId(personId));
		return personResult;
	}
	
	private void setInfo(JSONObject personResult) throws FaceppParseException, JSONException {
		personId = personResult.getString(KEY_PERSON_ID);
		personName = personResult.getString(KEY_PERSON_NAME);
		tag = personResult.getString(KEY_TAG);
				
		JSONArray facesResult = personResult.getJSONArray(KEY_FACE);
		for (int i=0; i<facesResult.length(); i++) {
			JSONObject faceResult = facesResult.getJSONObject(i);
			Face face = new Face(faceResult);
			faces.add(face);			
		}
		
		JSONArray groupsResult = personResult.getJSONArray(KEY_GROUP);
		for (int i=0; i<groupsResult.length(); i++) {
			JSONObject groupResult = groupsResult.getJSONObject(i);
			Group group = new Group(groupResult);
			groups.add(group);
		}
		
	}

}
