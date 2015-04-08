package kr.ac.kaist.hrhrp.type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class Face extends Init {

	private String faceId;
	private String tag;
	private Candidate candidate = null;
	private String imgUrl = null;
	
	private Position position = null;
	
	public Face() {	

	}
	
	public Face(String aFaceId) throws JSONException, FaceppParseException {
		setFace(getFace(aFaceId));
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
	
	public void setImgUrl(String aImgUrl) {
		imgUrl = aImgUrl;
	}
	
	public void setPosition(double width, double height, double center_x, double center_y) {
		position = new Position();
		position.setCenterX(center_x);
		position.setCenterY(center_y);
		position.setHeight(height);
		position.setWidth(width);	
	}
	
	private void setPosition(JSONObject posObj) throws JSONException {
		position = new Position();
		position.setCenterX(posObj.getJSONObject(KEY_POS_CENTER).getDouble("x"));
		position.setCenterY(posObj.getJSONObject(KEY_POS_CENTER).getDouble("y"));
		position.setHeight(posObj.getDouble(KEY_POS_HEIGHT));
		position.setWidth(posObj.getDouble(KEY_POS_WIDTH));	
	}
			
	private JSONObject getFace(String aFaceId) throws FaceppParseException, JSONException {	
		try {
			JSONObject faceInfoObj = httpRequests.infoGetFace(new PostParameters().setFaceId(aFaceId));
			faceInfoObj = faceInfoObj.getJSONArray(KEY_FACE_INFO).getJSONObject(0);
			return faceInfoObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void setFace(JSONObject faceResult) throws JSONException, FaceppParseException {
		faceId = (faceResult.has(KEY_FACE_ID))?faceResult.getString(KEY_FACE_ID):null;
		//tag = (faceResult.has(KEY_TAG))?faceResult.getString(KEY_TAG):null; //Occured error not a String
		
		JSONObject posObject = new JSONObject();
		if (faceResult.has(KEY_POSITION)) {
			posObject = faceResult.getJSONObject(KEY_POSITION);		
			System.out.println("setFace:"+posObject.toString());
			setPosition(posObject);
		} 
		
		JSONArray candidatesResult = (faceResult.has(KEY_CANDIDATE))?faceResult.getJSONArray(KEY_CANDIDATE):null;

		if (candidatesResult != null && candidatesResult.length() > 0) {
			JSONObject candidateResult = candidatesResult.getJSONObject(0);	
			candidate = new Candidate();
			candidate.setPersonId(candidateResult.getString(KEY_PERSON_ID));
			candidate.setPersonName(candidateResult.getString(KEY_PERSON_NAME));
			//candidate.setTag(candidateResult.getString(KEY_TAG));
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
	
	public Position getPosition() {
		return position;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public class Position {
		private double center_x;
		private double center_y;
		private double height;
		private double width;
		
		public void setCenterX(double center_x) {
			this.center_x = center_x;
		}
		
		public void setCenterY(double center_y) {
			this.center_y = center_y;
		}
		
		public void setHeight(double height) {
			this.height = height;
		}
		
		public void setWidth(double width) {
			this.width = width;
		}
		
		public double getCenterX() {
			return center_x;
		}
		
		public double getCenterY() {
			return center_y;
		}
		
		public double getHeight() {
			return height;
		}
		
		public double getWidth() {
			return width;
		}
		
		
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
