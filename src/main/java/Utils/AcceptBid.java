package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class AcceptBid {
	
public AcceptBid() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void acceptBidAPI(String authToken, int bidId) throws IOException {
		
		testdata = new TestData();
		json.addProperty("id", bidId);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/customer/acceptBid";
		
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
	
	public String getStatusValue() {
		return jsonresponse.getString("data.status").toString();
	}

	


}
