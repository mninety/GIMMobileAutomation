package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class GetListOfBidsForTrip {

	public GetListOfBidsForTrip(){
		
	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void GetListOfBidsForTripAPI(String authToken, int page, 
			int tripId, int size) throws IOException {
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/getListOfBidsForTrip";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("tripId", tripId).
				queryParam("size", size).
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
	
	public String getBidID() {
		return jsonresponse.getString("data.contentList[0].bidId").toString();
	}
	
	public String getDriveID() {
		return jsonresponse.getString("data.contentList[0].driverId").toString();
	}
	
	public String getBidCount() {
		return jsonresponse.getString("data.numberOfResults").toString();
	}
	
	
	
	
	
	
	
	
	
}
