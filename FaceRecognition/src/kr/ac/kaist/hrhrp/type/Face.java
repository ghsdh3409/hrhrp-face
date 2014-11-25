package kr.ac.kaist.hrhrp.type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;


public class Face extends Init {

	private String faceId;
	private String tag;
	private Candidate candidate = null;

	public Face() {	

	}

	public Face(JSONObject faceResult) throws FaceppParseException, JSONException {
		setFace(faceResult);
	}

	public void setFaceId(String aFaceId) {
		faceId = aFaceId;
	}

	public void setTag(String aTag) {
		tag = aTag;
	}

	private void setFace(JSONObject faceResult) throws JSONException {
		faceId = (faceResult.has(KEY_FACE_ID))?faceResult.getString(KEY_FACE_ID):null;
		tag = (faceResult.has(KEY_TAG))?faceResult.getString(KEY_TAG):null;
		
		JSONArray candidatesResult = (faceResult.has(KEY_CANDIDATE))?faceResult.getJSONArray(KEY_CANDIDATE):null;

		if (candidatesResult != null && candidatesResult.length() > 0) {
			JSONObject candidateResult = candidatesResult.getJSONObject(0);	
			candidate = new Candidate();
			candidate.setPersonId(candidateResult.getString(KEY_PERSON_ID));
			candidate.setPersonName(candidateResult.getString(KEY_PERSON_NAME));
			candidate.setTag(candidateResult.getString(KEY_TAG));
			candidate.setConfidence(candidateResult.getDouble(KEY_CONFIDENCE));
		}
	}

	public String getFaceId() {
		return faceId;
	}

	public String getFaceTag() {
		return tag;
	}

	public Candidate getCandidate() {
		return candidate;
	}


	public class Candidate {
		private String personId;
		private String personName;
		private String tag;
		private Double confidence;

		public void setPersonId(String aPersonId) {
			personId = aPersonId;
		}

		public void setPersonName(String aPersonName) {
			personName = aPersonName;
		}

		public void setTag(String aTag) {
			tag = aTag;
		}

		public void setConfidence(Double aConfidence) {
			confidence = aConfidence;
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

		public Double getConfidence() {
			return confidence;
		}

	}

}
