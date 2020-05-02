package Utils;

import java.io.IOException;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class CreateTrip {
	
	public CreateTrip() {
		
	}
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void NewTrip(String authToken, String datetimeutc, String datetimelocal, 
			double pickuplat, double pickuplon, double dropofflat, double dropofflan, String pickupaddress,
			String dropoffaddress, int trucktype, int trucksize, int goodstype,
			String othergoodtype, String splinstructions, String image, String paymenttype,
			int trucklength, int truckwidth, int truckheight) throws IOException {
		
		testdata = new TestData();
		json.addProperty("datetimeUtc", datetimeutc);
		json.addProperty("datetimeLocal", datetimelocal);
		json.addProperty("pickUplat", pickuplat);
		json.addProperty("pickUplon", pickuplon);
		json.addProperty("dropOfflat", dropofflat);
		json.addProperty("dropOfflon", dropofflan);
		json.addProperty("pickUpAddress", pickupaddress);
		json.addProperty("dropOffAddress", dropoffaddress);
		json.addProperty("truckType", trucktype);
		json.addProperty("truckSize", trucksize);
		json.addProperty("goodsType", goodstype);
		json.addProperty("otherGoodType", othergoodtype);
		json.addProperty("specialInsturctions", splinstructions);
		json.addProperty("image", image);
		json.addProperty("paymentType", paymenttype);
		json.addProperty("truckLength", trucklength);
		json.addProperty("truckWidth", truckwidth);
		json.addProperty("truckHeight", truckheight);
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/trip/createTrip";
		
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
	
	public int getTripID() {
		return Integer.parseInt(jsonresponse.getString("data.tripId"));
	}
	
	



}
