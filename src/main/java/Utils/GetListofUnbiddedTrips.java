package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class GetListofUnbiddedTrips {
	
	public GetListofUnbiddedTrips(){
		
	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void GetListofUnbiddedTripsAPI(String authToken) throws IOException {
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/trip/getListOfUnbiddedTrips";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", 1).
				queryParam("size", 10).
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
	
	public String getauthToken() {
		return jsonresponse.getString("data.token").toString();
	}
	

}
