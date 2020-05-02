package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class GIMSignIn {
	
	public GIMSignIn() {
		
	}
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void SignInAPI(String devicetoken, String devicetype, String mobileno,
			String password, int roleid) throws IOException {
		testdata = new TestData();
		json.addProperty("deviceToken", devicetoken);
		json.addProperty("deviceType", devicetype);
		json.addProperty("mobileNum", mobileno);
		json.addProperty("password", password);
		json.addProperty("roleId", roleid);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/signIn";
		
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
	
	public String getDriverlicenseno() {
		return jsonresponse.getString("data.driverLicenseNumber");
	}
	
	public String getUserID() {
		return jsonresponse.getString("data.id");
	}
	
}
