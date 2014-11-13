package kr.ac.kaist.hrhrp.type;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.util.Log;
import kr.ac.kaist.hrhrp.util.RandomString;

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

	public Person() throws FaceppParseException, JSONException {

	}

	public Person(String aPersonId) throws FaceppParseException, JSONException {
		setInfo(getInfo(aPersonId));
	}

	public Person(JSONObject personResult) throws FaceppParseException, JSONException {
		setInfo(personResult);
	}

	public void addFace(Face face) throws FaceppParseException {
		String faceId = face.faceId;

		Log.log(DEBUG_MODE, httpRequests.personAddFace(new PostParameters().setPersonId(personId).setFaceId(faceId)).toString());
	}

	public void update() throws FaceppParseException {
		PostParameters params = new PostParameters();
		if (personName != null)
			params.setPersonName(personName);
		if (tag != null)
			params.setTag(tag);
		if (personId != null)
			params.setPersonId(personId);
		if (personName != null)
			params.setPersonName(personName);

		Log.log(DEBUG_MODE, httpRequests.personSetInfo(params).toString());		
	}

	public void create() throws FaceppParseException {
		PostParameters params = new PostParameters();
		if (personName != null)
			params.setPersonName(personName);
		else 
			params.setPersonName("PERSON_" + RandomString.getRandomString(30));
		if (tag != null)
			params.setTag(tag);
		ArrayList<String> faceIds = new ArrayList<String>();
		for (int i=0; i<faces.size(); i++) {
			Face face = faces.get(i);
			faceIds.add(face.getFaceId());
		}
		params.setFaceId(faceIds);

		ArrayList<String> groupNames = new ArrayList<String>();
		for (int i=0; i<groups.size(); i++) {
			Group group = groups.get(i);
			groupNames.add(group.getGroupName());
		}
		params.setGroupName(groupNames);

		Log.log(DEBUG_MODE, httpRequests.personCreate(params).toString());
	}

	public void setPersonId(String aPersonId) {
		personId = aPersonId;
	}

	public void setPersonName(String aPersonName) {
		personName = aPersonName;
	}

	public void setTag(String aTag) {
		tag = aTag;
	}

	public void setGroup(Group aGroup) {
		groups.add(aGroup);
	}

	public void setGroupVars(ArrayList<Group> aGroups) {
		groups = aGroups;
	}

	public void setFace(Face aFace) {
		faces.add(aFace);
	}

	public void setFaceVars(ArrayList<Face> aFaces) {
		faces = aFaces;
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
		Log.log(DEBUG_MODE, personResult.toString());
		return personResult;
	}

	private void setInfo(JSONObject personResult) throws FaceppParseException, JSONException {
		if (personResult.has(KEY_PERSON_ID))
			personId = personResult.getString(KEY_PERSON_ID);
		if (personResult.has(KEY_PERSON_NAME))
			personName = personResult.getString(KEY_PERSON_NAME);
		if (personResult.has(KEY_TAG))
			tag = personResult.getString(KEY_TAG);
		if (personResult.has(KEY_FACE)) {
			JSONArray facesResult = personResult.getJSONArray(KEY_FACE);
			for (int i=0; i<facesResult.length(); i++) {
				JSONObject faceResult = facesResult.getJSONObject(i);
				Face face = new Face(faceResult);
				faces.add(face);			
			}
		}
		if (personResult.has(KEY_GROUP)) {
			JSONArray groupsResult = personResult.getJSONArray(KEY_GROUP);
			for (int i=0; i<groupsResult.length(); i++) {
				JSONObject groupResult = groupsResult.getJSONObject(i);
				Group group = new Group(groupResult);
				groups.add(group);
			}
		}
	}

}
