package kr.ac.kaist.hrhrp;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;

import kr.ac.kaist.hrhrp.type.Init;

public class Test extends Init {

	public static void main(String[] args) throws FaceppParseException {
		// TODO Auto-generated method stub
		System.out.println(httpRequests.groupCreate(new PostParameters().setGroupName("HRHRP_Test")));
	}
}
