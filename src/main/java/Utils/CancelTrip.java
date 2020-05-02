package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class CancelTrip {
	
public CancelTrip() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void CancelTripAPI(String authToken, int cancelreasonId, 
			int tripId) throws IOException {
		
		testdata = new TestData();
		json.addProperty("id", tripId);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/cancelTrip/{cancelReasonId}";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				pathParam("cancelReasonId", cancelreasonId).
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
	
	public String getStatusValue() {
		return jsonresponse.getString("data.status").toString();
	}

}
