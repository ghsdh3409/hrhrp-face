package kr.ac.kaist.hrhrp;

import com.facepp.error.FaceppParseException;
import com.facepp.http.PostParameters;

import kr.ac.kaist.hrhrp.type.Init;

public class Test extends Init {

	public static void main(String[] args) throws FaceppParseException {
		// TODO Auto-generated method stub
		//System.out.println(httpRequests.groupCreate(new PostParameters().setGroupName("HRHRP_Test")));
		System.out.println(httpRequests.infoGetPersonList());
		System.out.println(httpRequests.personGetInfo(new PostParameters().setPersonId("8f2236eed257e01187410bcec2198282")));
		System.out.println(httpRequests.infoGetFace(new PostParameters().setFaceId("408579b7c666c694af27639488cc59e6")));
		
	}
}
