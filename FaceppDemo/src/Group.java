import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;


public class Group extends Init {
	
	private String groupId;
	private String tag;
	private String groupName;
	private ArrayList<Person> persons = new ArrayList<Person>();
	
	public Group(JSONObject groupResult) throws JSONException, FaceppParseException {
		setGroup(groupResult);
	}
	
	private void setGroup(JSONObject groupResult) throws JSONException, FaceppParseException {
		JSONArray personsResult = groupResult.getJSONArray(KEY_PERSON);
		
		for (int i=0; i<personsResult.length(); i++) {
			JSONObject personResult = personsResult.getJSONObject(i);
			Person person = new Person(personResult);
			persons.add(person);
		}
		
		groupId = groupResult.getString(KEY_GROUP_ID);
		tag = groupResult.getString(KEY_TAG);
		groupName = groupResult.getString(KEY_GROUP_NAME);
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public ArrayList<Person> getPersons() {
		return persons;
	}
}
