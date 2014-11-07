import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;


public class Face extends Init {

	String faceId;
	String tag;
	Candidate candidate = new Candidate();

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
		faceId = faceResult.getString(KEY_FACE_ID);
		tag = faceResult.getString(KEY_TAG);

		JSONArray candidatesResult = faceResult.getJSONArray(KEY_CANDIDATE);

		if (candidatesResult != null) {
			JSONObject candidateResult = candidatesResult.getJSONObject(0);	
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
