package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class UserPersonalProfile {
	
public UserPersonalProfile(){
		
	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void UserPersonalProfileAPI(String authToken, int roleId) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/user/profile/{roleId}";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				pathParam("roleId", roleId).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());
		
		jsonresponse.prettyPrint();
			
	}
	
	public String getDataValue() {
		return jsonresponse.getString("data");
	}
	
	public String getSuccessValue() {
		return jsonresponse.getString("success");
	}
	
	public String getMessage() {
		return jsonresponse.getString("message").toString();
	}
	
	public String getResponseCode() {
		return jsonresponse.getString("responseCode").toString();
	}
	
	
	

}
