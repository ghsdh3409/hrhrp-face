import com.facepp.http.HttpRequests;


public class Init {
	
	static String KEY_FACE = "face";
	static String KEY_FACE_ID = "face_id";
	static String KEY_CANDIDATE = "candidate";
	static String KEY_PERSON = "person";
	static String KEY_PERSON_ID = "person_id";
	static String KEY_PERSON_NAME = "person_name";
	static String KEY_TAG = "tag";
	static String KEY_CONFIDENCE = "confidence";
	static String KEY_GROUP = "group";
	static String KEY_GROUP_ID = "group_id";
	static String KEY_GROUP_NAME = "group_name";
	
	
	static Double THRESHOLD = 60.0;
	HttpRequests httpRequests = new HttpRequests("72260294f4ea259086b05fc90ed893f6", "5faC_p_zlFxrKi3o8boVlddTdNpMOiEJ", false, true);
}
