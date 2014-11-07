import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;


public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HttpRequests httpRequests = new HttpRequests("72260294f4ea259086b05fc90ed893f6", "5faC_p_zlFxrKi3o8boVlddTdNpMOiEJ", false, true);

		HashMap<String, ArrayList<String>> imageSet = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> imageSet2 = new HashMap<String, ArrayList<String>>();

		imageSet.put("WonBin", new ArrayList<String>());

		imageSet.get("WonBin").add("http://pds.joins.com/news/component/newsen/201309/11/201309111355521910_1.jpg");
		imageSet.get("WonBin").add("http://image.ytn.co.kr/osen/2014/05/20130529_1369792242_43733700_1.jpg");
		imageSet.get("WonBin").add("http://image.tvdaily.co.kr/upimages/gisaimg/201302/1361928245_03120933-b.jpg");

		imageSet2.put("WonBin", new ArrayList<String>());

		imageSet2.get("WonBin").add("http://www.bigjungbo.com/xe/files/attach/images/163/825/047/578a17e481940d85a81c5e3c7f184c80.jpg");
		imageSet2.get("WonBin").add("http://cfile204.uf.daum.net/image/196BF3244C68E0585CC4D8");
		imageSet2.get("WonBin").add("http://c.ask.nate.com/imgs/qrsi.php/11794736/21220407/0/1/A/13.JPG");

		imageSet.put("SongJaeLim", new ArrayList<String>());

		imageSet.get("SongJaeLim").add("http://spnimage.edaily.co.kr/images/photo/files/NP/S/2014/05/PS14051400060.jpg");
		imageSet.get("SongJaeLim").add("http://www.obsnews.co.kr/news/photo/201305/709405_91641_1714.jpg");
		imageSet.get("SongJaeLim").add("http://pds21.egloos.com/pds/201404/12/75/f0385875_5349377509daf.jpg");

		imageSet2.put("SongJaeLim", new ArrayList<String>());

		imageSet2.get("SongJaeLim").add("http://pds.joins.com/news/component/newsen/201407/30/201407301623403110_1.jpg");
		imageSet2.get("SongJaeLim").add("http://www.stardailynews.co.kr/news/photo/201408/37983_46883_4458.jpg");
		imageSet2.get("SongJaeLim").add("http://img.tvreport.co.kr/images/20140407/20140407_1396871560_23819500_1.jpg?1403427871");

		imageSet.put("KimYoungKwang", new ArrayList<String>());

		imageSet.get("KimYoungKwang").add("http://img.tvreport.co.kr/images/20120522/20120522_1337671828_14172000_1.jpg");
		imageSet.get("KimYoungKwang").add("http://nimg.nate.com/orgImg/tv/2013/01/20/1358649451_452940.jpg");
		imageSet.get("KimYoungKwang").add("http://pds.joins.com/news/component/newsen/201205/30/201205301357263010_1.jpg");

		imageSet2.put("KimYoungKwang", new ArrayList<String>());

		imageSet2.get("KimYoungKwang").add("http://cfile21.uf.tistory.com/image/2534AB43525AA1D72EE7C0");
		imageSet2.get("KimYoungKwang").add("http://cfile24.uf.tistory.com/image/2143014252A05CE9172169");
		imageSet2.get("KimYoungKwang").add("http://i.ytimg.com/vi/j59nlhbV44Q/maxresdefault.jpg");

		String groupName = "DaehoonKim_Test";

		long addFaceTS1 = 0;
		long addFaceTS2 = 0;
		long trainTS1 = 0;
		long trainTS2 = 0;
		long recogTS1 = 0;
		long recogTS2 = 0;
		
		long addFace2TS1 = 0;
		long addFace2TS2 = 0;
		long train2TS1 = 0;
		long train2TS2 = 0;
		long recog2TS1 = 0;
		long recog2TS2 = 0;
		
		try {
/*
			System.out.println(Charset.forName("UTF-8").name());

			System.out.println("FacePlusPlus API Test:");

			addFaceTS1 = System.currentTimeMillis();

			for(String personName : imageSet.keySet()) {
				System.out.println("\nperson/create");
				System.out.println(httpRequests.personCreate(new PostParameters().setPersonName(personName)));
				for(String imageUrl : imageSet.get(personName)) {
					JSONObject result = httpRequests.detectionDetect(new PostParameters().setUrl(imageUrl));
					System.out.println(result);
					System.out.println("\nperson/add_face");
					for (int i = 0; i < result.getJSONArray("face").length(); ++i)
						System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName(personName).setFaceId(
								result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
				}
			}

			addFaceTS2 = System.currentTimeMillis();

			//info/get_person_list
			System.out.println("\ninfo/get_person_list");
			JSONObject personList = httpRequests.infoGetPersonList();
			ArrayList<String> personNameList = new ArrayList<String>();

			for (int i = 0; i < personList.getJSONArray("person").length(); ++i) {
				String personName = personList.getJSONArray("person").getJSONObject(i).getString("person_name");
				personNameList.add(personName);
			}

			//-----------------Group-----------------
			//group/create
			System.out.println("\ngroup/create");

			System.out.println(httpRequests.groupCreate(new PostParameters().setGroupName(groupName)));


			//group/add_person
			System.out.println("\ngroup/add_person");

			new PostParameters().setGroupName(groupName).setPersonName(personNameList).getMultiPart().writeTo(System.out);
			System.out.println(httpRequests.groupAddPerson(new PostParameters().setGroupName(groupName).setPersonName(personNameList)));


			//group/get_info
			System.out.println("\ngroup/get_info");
			System.out.println(httpRequests.groupGetInfo(new PostParameters().setGroupName(groupName)));

*/
			//recognition/train
			JSONObject syncRet = null; 

			System.out.println("\ntrain/Identify");

			trainTS1 = System.currentTimeMillis();
			syncRet = httpRequests.trainIdentify(new PostParameters().setGroupName(groupName));	
			trainTS2 = System.currentTimeMillis();

			System.out.println(syncRet);
			System.out.println(httpRequests.getSessionSync(syncRet.getString("session_id")));


			//recognition/recognize
			System.out.println("\nrecognition/identify");

			String[] queryWonUrl = {"Won", "http://dimg.donga.com/wps/NEWS/IMAGE/2010/07/08/29730765.2.jpg"};
			String[] querySongUrl = {"Song", "http://img.tvreport.co.kr/images/20131015/20131015_1381808372_86299500_1.jpg?1381866335"};
			String[] queryKimUrl = {"Kim", "http://www.andew.co.kr/DATA/cheditor/wsDRX1mLivtwc93Qw5j7BguN8ApJBNJS.jpg"};

			String[][] queryUrls = {queryKimUrl, queryWonUrl, querySongUrl};

			for (String[] queryUrl : queryUrls) {
		
				System.out.println("Query man : " + queryUrl[0]);

				recogTS1 = System.currentTimeMillis();
				
				JSONObject recogResult = httpRequests.recognitionIdentify(
						new PostParameters().setGroupName(groupName).setUrl(queryUrl[1]));

				recogTS2 = System.currentTimeMillis();
				
				System.out.println(recogResult);

				JSONArray candidates = recogResult.getJSONArray("face").getJSONObject(0).getJSONArray("candidate");

				for (int i=0; i<candidates.length(); i++) {
					String candidateName = candidates.getJSONObject(i).getString("person_name");
					double confidence = candidates.getJSONObject(i).getDouble("confidence");

					System.out.println(candidateName + '\t' + confidence);				
				}

				System.out.println("");

			}

			addFace2TS1 = System.currentTimeMillis();

			for(String personName : imageSet2.keySet()) {
				for(String imageUrl : imageSet2.get(personName)) {
					JSONObject result = httpRequests.detectionDetect(new PostParameters().setUrl(imageUrl));
					System.out.println(result);
					System.out.println("\nperson/add_face");
					for (int i = 0; i < result.getJSONArray("face").length(); ++i)
						System.out.println(httpRequests.personAddFace(new PostParameters().setPersonName(personName).setFaceId(
								result.getJSONArray("face").getJSONObject(i).getString("face_id"))));
				}
			}

			addFace2TS2 = System.currentTimeMillis();

			//recognition/train

			System.out.println("\ntrain/Identify");

			train2TS1 = System.currentTimeMillis();
			syncRet = httpRequests.trainIdentify(new PostParameters().setGroupName(groupName));	
			train2TS2 = System.currentTimeMillis();

			System.out.println(syncRet);
			System.out.println(httpRequests.getSessionSync(syncRet.getString("session_id")));


			//recognition/recognize
			System.out.println("\nrecognition/identify");

			for (String[] queryUrl : queryUrls) {
		
				System.out.println("Query man : " + queryUrl[0]);

				recog2TS1 = System.currentTimeMillis();
				
				JSONObject recogResult = httpRequests.recognitionIdentify(
						new PostParameters().setGroupName(groupName).setUrl(queryUrl[1]));

				recog2TS2 = System.currentTimeMillis();
				
				System.out.println(recogResult);

				JSONArray candidates = recogResult.getJSONArray("face").getJSONObject(0).getJSONArray("candidate");

				for (int i=0; i<candidates.length(); i++) {
					String candidateName = candidates.getJSONObject(i).getString("person_name");
					double confidence = candidates.getJSONObject(i).getDouble("confidence");

					System.out.println(candidateName + '\t' + confidence);				
				}

				System.out.println("");

			}
			
			System.out.println("1 :: Create Person + Add Face : " + (addFaceTS2 - addFaceTS1));
			System.out.println("1 :: Training : " + (trainTS2 - trainTS1));
			System.out.println("1 :: Recognition : " + (recogTS2 - recogTS1));
			
			System.out.println("2 :: Add Face : " + (addFace2TS2 - addFace2TS1));
			System.out.println("2 :: Training : " + (train2TS2 - train2TS1));
			System.out.println("2 :: Recognition : " + (recog2TS2 - recog2TS1));
			
		} catch(FaceppParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {

		}
	}
}


