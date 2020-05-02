package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;

public class UpdatePersonalInfo {
	
	public UpdatePersonalInfo() {
		
	}
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonPath jsonresponse;
	
	public void UpdatePersonalInfoAPI(String authToken, int roleID, String dob, String email, String name, 
			String profilePhoto, String location, String nationalId, String nationalIdBackPhoto,
			String nationalIdFrontPhoto, int smartphoneStatus, int district,  String bkashNumber, String registrationPoint) throws IOException {
	
		testdata = new TestData();
		JsonObject json = new JsonObject();
    	  
		json.addProperty("roleId", roleID);
		json.addProperty("dob", dob);
		json.addProperty("email", email);
		json.addProperty("name", name);
		json.addProperty("profilePhoto", profilePhoto);
		json.addProperty("name", rand.generateRandomName());
		json.addProperty("location", location);	
		json.addProperty("nationalId", nationalId);
		json.addProperty("nationalIdBackPhoto", nationalIdBackPhoto);
		json.addProperty("nationalIdFrontPhoto", nationalIdFrontPhoto);
		json.addProperty("nationalIdFrontPhoto", testdata.properties.getProperty("image"));
		json.addProperty("smartphoneStatus", smartphoneStatus);
		json.addProperty("district", district);
		json.addProperty("bkashNumber", bkashNumber);
		json.addProperty("registrationPoint", registrationPoint);
	
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/user/update/personalInfo";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}
	
	public void UpdateNID(String authToken, int roleID, String nationalId) throws IOException {
	
		testdata = new TestData();
		JsonObject json = new JsonObject();
    	
		json.addProperty("roleId", roleID);
		json.addProperty("nationalId", nationalId);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/user/update/personalInfo";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}
	
	public void UpdateDistrict(String authToken, int roleID, int district) throws IOException {
	
		testdata = new TestData();
		JsonObject json = new JsonObject();
    	  
		json.addProperty("roleId", roleID);
		json.addProperty("district", district);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/user/update/personalInfo";
		
		jsonresponse = new JsonPath(RestAssured
				.given()
				.header("authtoken", authToken)
				.contentType("application/json")
				.body(json.toString())
				.when()
				.post()
				.asString());
		
		jsonresponse.prettyPrint();
					
}
	
	public String getResponseMessage() {
		return  jsonresponse.getString("message");
	}
	
	public int getResponseCode() {
		return Integer.parseInt(jsonresponse.getString("responseCode"));
	}
	
	

}
