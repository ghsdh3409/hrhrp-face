package kr.ac.kaist.hrhrp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBWriter {
	private final String url = "";
	private final String className = "com.mysql.jdbc.Driver";
	
	private Connection conn = null;
	
	private final String SELECT_IMAGE_SQL = "SELECT url FROM ### WHERE state = 1 LIMIT ?";
	private final String INSERT_IMAGE_INFO_SQL = "INSERT INTO ### (url, time, lat, lng, owner) VALUES (?, ?, ? ,?, ?)";
	private final String UPDATE_IMAGE_STATE_SQL = "UPDATE ### SET state = ? WHERE image_id = ?";
	private final String INSERT_PERSON_INFO_SQL = "INSERT INTO ### (person_id, person_name) VALUES (?, ?)";
	private final String INSERT_IMAGE_PERSON_SQL = "INSERT INTO ### (image_id, person_id) VALUES (?, ?)";
	private final String INSERT_PERSON_RELATION_SQL = "INSERT INTO ### (person1_id, person2_id, relation) VALUES (?, ?)";
	private final String UPDATE_EXTERNAL_INFO_SQL = "UPDATE ### SET weather = ?, address = ?, buildingName = ? WHERE image_id = ?";
	
	public DBWriter() {
		init();
	}
	
	private void init() {
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> selectNewImage(int num) {
		ArrayList<String> imageUrls = new ArrayList<String>();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_IMAGE_SQL);
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String imageUrl = rs.getString(1);
				imageUrls.add(imageUrl);
			}
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageUrls;
	}
	
	public void updateImageState(String imageId, int state) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_IMAGE_STATE_SQL);
			ps.setInt(1, state);
			ps.setString(2, imageId);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertPersonInfo(String personId, String personName) {
		//TODO insert person information to DB
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT_PERSON_INFO_SQL);
			ps.setString(1, personId);
			ps.setString(2, personName);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertImagePerson(String imageId, String personId) {
		//TODO insert persons in image to DB
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT_IMAGE_PERSON_SQL);
			ps.setString(1, imageId);
			ps.setString(2, personId);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertPersonRelation(String personId1, String personId2, String relation) {
		//TODO insert a relationship of person to DB
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT_PERSON_RELATION_SQL);
			ps.setString(1, personId1);
			ps.setString(2, personId2);
			ps.setString(2, relation);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertImageInfo(String imageId, long imageTime, float lat, float lng, String ownerId) {
		//TODO insert an initial information(url, time, lat, lng) of image to DB
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT_IMAGE_INFO_SQL);
			ps.setString(1, imageId);
			ps.setLong(2, imageTime);
			ps.setFloat(3, lat);
			ps.setFloat(4, lng);
			ps.setString(5, ownerId);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateExternalInfo(String imageId, String weather, String address, String buildingName) {
		//TODO update a external information(weather, address, building name) of image to DB
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(UPDATE_EXTERNAL_INFO_SQL);
			ps.setString(1, weather);
			ps.setString(2, address);
			ps.setString(3, buildingName);
			ps.setString(4, imageId);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
