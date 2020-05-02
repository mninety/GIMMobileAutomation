package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class Tracker_Action {

public Tracker_Action(){
		
	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void AddTruckTrackerAPI(String authToken, int truckId, String trackerType, String secretKey,
			String username, String password, String vehicleId) throws IOException {
		testdata = new TestData();
		JsonObject json = new JsonObject();
		
		
		//json.addProperty("authToken", authToken);
		json.addProperty("truckId", truckId);
		json.addProperty("trackerType", trackerType);
		json.addProperty("secretKey", secretKey);
		json.addProperty("username", username);
		json.addProperty("password", password);
		json.addProperty("vehicleId", vehicleId);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/tracker";
		
		jsonresponse = new JsonPath(RestAssured.
				given().

				header("authToken", authToken ).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());
		
		jsonresponse.prettyPrint();
			
	}
	
	public void ActivateTruckTrackerAPI(String authToken,  int truckId) throws IOException {
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/tracker/{truckId}/activate";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				pathParam("truckId", truckId).
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());
		
		jsonresponse.prettyPrint();
			
	}
	
	public void DeactivateTruckTrackerAPI(String authToken,  int truckId) throws IOException {
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/tracker/{truckId}/deactivate";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				pathParam("truckId", truckId).
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				post().
				asString());
		
		jsonresponse.prettyPrint();
			
	}
	
	public void UpdateTruckTrackerAPI(String authToken,  int truckTrackerId, int truckId,
			String trackerType, String secretKey, String username, String password, String vehicleId) throws IOException {
		
		testdata = new TestData();
		JsonObject json = new JsonObject();
		
		json.addProperty("truckId", truckId);
		json.addProperty("trackerType", trackerType);
		json.addProperty("secretKey", secretKey);
		json.addProperty("username", username);
		json.addProperty("password", password);
		json.addProperty("vehicleId", vehicleId);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/tracker/{truckTrackerId}";
		
		jsonresponse = new JsonPath(RestAssured.
				given().

				header("authToken", authToken ).
				pathParam("truckTrackerId", truckTrackerId).
				contentType("application/json").
				body(json.toString()).
				when().
				put().
				asString());
		
		jsonresponse.prettyPrint();
			
			
	}
	
	
	public void DeleteTruckTrackerAPI(String authToken,  int truckId) throws IOException {
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/tracker/{truckId}";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				pathParam("truckId", truckId).
				header("authtoken", authToken).
				contentType("application/json").
				body(json.toString()).
				when().
				delete().
				asString());
		
		jsonresponse.prettyPrint();
			
	}
	
	public String getDataValue() {
		return jsonresponse.getString("data");
	}
	
	public String getStatusValue() {
		return jsonresponse.getString("data.status");
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
	
	public String getErrorCode() {
		return jsonresponse.getString("errorCode").toString();
	}
	
}
