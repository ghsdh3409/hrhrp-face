package kr.ac.kaist.hrhrp.type;

import com.facepp.http.HttpRequests;


public class Init {
	
	public static String KEY_FACE = "face";
	public static String KEY_FACE_ID = "face_id";
	public static String KEY_CANDIDATE = "candidate";
	public static String KEY_PERSON = "person";
	public static String KEY_PERSON_ID = "person_id";
	public static String KEY_PERSON_NAME = "person_name";
	public static String KEY_TAG = "tag";
	public static String KEY_CONFIDENCE = "confidence";
	public static String KEY_GROUP = "group";
	public static String KEY_GROUP_ID = "group_id";
	public static String KEY_GROUP_NAME = "group_name";
	
	
	public static Double THRESHOLD = 60.0;
	public HttpRequests httpRequests = new HttpRequests("72260294f4ea259086b05fc90ed893f6", "5faC_p_zlFxrKi3o8boVlddTdNpMOiEJ", false, true);
}
