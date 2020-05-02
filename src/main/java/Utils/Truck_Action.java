package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class Truck_Action {

	public Truck_Action() {

	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void ApproveTruck(String authToken, int truckid) throws IOException {

		testdata = new TestData();
		
		json.addProperty("truckId", truckid);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/approveTruck";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										contentType("application/json").
										body(json.toString()).
										when().
										post().
										asString());

		 jsonresponse.prettyPrint();

	}
	
	
	public void RejectTruck(String authToken, int truckid, String rejectioncomment) throws IOException {

		testdata = new TestData();
		
		json.addProperty("truckId", truckid);
		json.addProperty("comment", rejectioncomment);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/rejectTruck";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
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
	
	
}
