package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.type.Face;
import kr.ac.kaist.hrhrp.type.Init;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class FaceDetector extends Init {
	private String faceUrl;

	public FaceDetector(String url) throws FaceppParseException {
		faceUrl = url;
	}
	
	public ArrayList<Face> getFaces() throws FaceppParseException, JSONException {
		JSONObject result =  httpRequests.detectionDetect(new PostParameters().setUrl(faceUrl));
		JSONArray faceResult = result.getJSONArray("face");
		
		ArrayList<Face> faces = new ArrayList<Face>();
		
		for (int i=0; i<faceResult.length(); i++) {
			Face face = new Face(faceResult.getJSONObject(i));
			faces.add(face);
		}
		
		return faces;		
	}
	
	public void setFaceUrl(String url) {
		faceUrl = url;
	}
	
	public String getFaceUrl() {
		return faceUrl;
	}
}
