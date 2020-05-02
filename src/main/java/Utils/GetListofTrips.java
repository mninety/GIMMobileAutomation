package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class GetListofTrips {
	
	public GetListofTrips() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void GetListofTripsAPI(String authToken, int filter, int page, int size) throws IOException {
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/getListOfTrips";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("filter", filter).
				queryParam("page", page).
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
	
	public String getTripID() {
		return jsonresponse.getString("data.contentList[0].id").toString();
	}
	
	public String getTripNumber() {
		return jsonresponse.getString("data.contentList[0].tripNumber").toString();
	}
	
	public String getTripsCount() {
		return jsonresponse.getString("data.numberOfResults").toString();
	}

}
