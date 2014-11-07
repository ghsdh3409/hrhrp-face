import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;


public class FaceDetiction extends Init {
	private String faceUrl;

	public FaceDetiction(String url) throws FaceppParseException {
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
