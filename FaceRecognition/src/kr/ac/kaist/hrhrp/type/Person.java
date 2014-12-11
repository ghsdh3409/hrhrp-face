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

	private String personId = null;
	private String personName = null;
	private String tag = null;

	private ArrayList<Group> groups = new ArrayList<Group>();

	private ArrayList<Face> faces = new ArrayList<Face>();

	public Person() {

	}

	public Person(String aPersonId, String keyType) {
		try {
			setInfo(getInfo(aPersonId, keyType));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Person(JSONObject personResult) throws FaceppParseException, JSONException {
		setInfo(personResult);
	}

	public void updateAddedFace(Face face) throws FaceppParseException {
		String faceId = face.getFaceId();
		Log.log(DEBUG_MODE, httpRequests.personAddFace(new PostParameters().setPersonId(personId).setFaceId(faceId)).toString());
		addFace(face);
	}

	public void update() throws FaceppParseException, JSONException {
		
		PostParameters params = new PostParameters();
		if (personId != null)
			params.setPersonId(personId);
		if (personName != null)
			params.setName(personName);
		if (tag != null)
			params.setTag(tag);

		JSONObject updateResult = null;
		try {
			updateResult = httpRequests.personSetInfo(params);
			Log.log(DEBUG_MODE,updateResult.toString());
		} catch (FaceppParseException e) {
			//TO DO : If name already exists, new person information has to transfer into existed person.
			e.printStackTrace();
			int code = getErrorCode(e.getMessage());
			if (code == ERR_EXIST_NAME) {
				updateExistedPerson();
			}
		}
	}

	public void create() throws FaceppParseException, JSONException {
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

		JSONObject createResult = httpRequests.personCreate(params);
		
		String personId = createResult.getString(KEY_PERSON_ID);
		setPersonId(personId);
				
		Log.log(DEBUG_MODE, createResult.toString());
	}

	public void delete() throws FaceppParseException {
		// TO DO : DELETE PERSON
		PostParameters params = new PostParameters();
		params.setPersonId(personId);
		Log.log(DEBUG_MODE, httpRequests.personDelete(params).toString());
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

	public void addFace(Face aFace) {
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

	private void updateExistedPerson() throws FaceppParseException, JSONException {
		Person person = new Person(personName, KEY_PERSON_NAME);
		for(Face face : faces) {
			person.updateAddedFace(face);
		}
				
		Person tempPerson = new Person(personId, KEY_PERSON_ID);
		tempPerson.delete();
		
		setPersonId(person.getPersonId());
		setPersonName(person.getPersonName());
		setTag(person.getTag());
		setGroupVars(person.getGroups());
		setFaceVars(person.getFaces());		
	}
	
	private JSONObject getInfo(String personId, String keyType) throws FaceppParseException {
		JSONObject personResult = null;
		if (keyType == KEY_PERSON_ID)
			personResult = httpRequests.personGetInfo(new PostParameters().setPersonId(personId));
		else if (keyType == KEY_PERSON_NAME)
			personResult = httpRequests.personGetInfo(new PostParameters().setPersonName(personId));
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
