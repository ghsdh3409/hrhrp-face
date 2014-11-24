package kr.ac.kaist.hrhrp;
import java.util.ArrayList;

import kr.ac.kaist.hrhrp.FaceRecognition;
import kr.ac.kaist.hrhrp.type.Image;
import kr.ac.kaist.hrhrp.type.Person;

public class Extractor {

	public Extractor() {
		
	}
	
	public void getInformation(String imageUrl, String groupName, String imageOwnerId) {
		Image image = new Image(imageUrl, imageOwnerId, groupName);
		ArrayList<Person> persons = faceRecogition(image);
		image.setPersons(persons);	
	}
	
	private ArrayList<Person> faceRecogition(Image image) {
		ArrayList<Person> persons = new ArrayList<Person>();
		FaceRecognition fr = new FaceRecognition(image.getGroupName());
		persons = fr.recognition(image.getUrl());
		return persons;
	}
	
	private void getExternalInfo(Image image) {
		// TO DO : get external information
	}
	
	/*
	private void updatePerson(String groupName, String newPersonName) {
		FaceRecognition fr = new FaceRecognition(groupName);
		for (Person person : fr.getNewPerson()) {
			fr.personUpdate(person, newPersonName);
		}
	}
	*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Extractor ex = new Extractor();
		String imageUrl = "http://img.tvreport.co.kr/images/20130807/20130807_1375853314_11589400_1.jpg";
		String groupName = "DaehoonKim_Test";
		String imageOwnerId = "DaehoonKim";
		
		ex.getInformation(imageUrl, groupName, imageOwnerId);
			
	}
}
