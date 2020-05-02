package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class SendBid {

public SendBid() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void SendBidAPI(String authToken, int bidId, String totalamount, 
			String advanceamount, int truckid, int driverid, String location) throws IOException {
		
		testdata = new TestData();
		json.addProperty("id", bidId);
		json.addProperty("totalAmount", totalamount);
		json.addProperty("advanceAmount", advanceamount);
		json.addProperty("truckId", truckid);
		json.addProperty("driverId", driverid);
		json.addProperty("location", location);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/sendBid";
		
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
	
	public String getBidID() {
		return jsonresponse.getString("data.bidId");
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
