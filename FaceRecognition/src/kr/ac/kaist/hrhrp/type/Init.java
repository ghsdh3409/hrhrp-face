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
	public static String PRE_NEW_PERSON = "PERSON_";
	public static String KEY_POSITION = "position";
	public static String KEY_POS_CENTER = "center";
	public static String KEY_POS_HEIGHT = "height";
	public static String KEY_POS_WIDTH = "width";
	public static String KEY_URL = "url";
	public static String KEY_FACE_INFO = "face_info";
	
	public static int ERR_EXIST_NAME = 1503;
	
	public static boolean DEBUG_MODE = true;
		
	public static Double THRESHOLD = 60.0;
	public static HttpRequests httpRequests = new HttpRequests("72260294f4ea259086b05fc90ed893f6", "5faC_p_zlFxrKi3o8boVlddTdNpMOiEJ", false, true);
	
	public int getErrorCode(String errorMsg) {
		String[] errorInfo = errorMsg.split("code=");
		int errCode = Integer.valueOf(errorInfo[1].split(",")[0]);
		return errCode;
	}
	
	public final String groupName = "HRHRP_Test";
	
}
