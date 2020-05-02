package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;

public class AdminLogin {
	
	public AdminLogin() {
		
	}
	TestData testdata;
	Randomgenerator rnd = new Randomgenerator();
	
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void adminLogInAPI(String deviceToken, String deviceType,
			String AdminEmail, String AdminPassword) throws IOException {
		
		testdata = new TestData();
		json.addProperty("deviceToken", deviceToken);
		json.addProperty("deviceType", deviceType);
		json.addProperty("email", AdminEmail);
		json.addProperty("password", AdminPassword);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/adminLogIn";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				contentType("application/json").
				body(json.toString()).
				when().
				post().
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
	
	public String getauthToken() {
		return jsonresponse.getString("data.token").toString();
	}
	
	


}
