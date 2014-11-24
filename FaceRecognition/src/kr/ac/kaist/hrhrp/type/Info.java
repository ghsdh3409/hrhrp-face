package kr.ac.kaist.hrhrp.type;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;

public class Info extends Init {
	public ArrayList<Person> getNewPersons() throws FaceppParseException, JSONException {
		ArrayList<Person> newPersons = new ArrayList<Person>();
		
		JSONObject result = httpRequests.infoGetPersonList();
		for (int i=0; i<result.getJSONArray(KEY_PERSON).length(); i++) {
			JSONObject personResult = result.getJSONArray(KEY_PERSON).getJSONObject(i);
			Person person = new Person(personResult);
			if (person.getPersonName().contains(PRE_NEW_PERSON)) {
				newPersons.add(person);
			}
		}
		return newPersons;
	}
}
