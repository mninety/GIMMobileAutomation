package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.Randomgenerator;
import Utils.TestData;
import Utils.Timing;

public class SubmitTruckApplication {
	
	TestData testdata;
	Randomgenerator rand = new Randomgenerator();
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	Timing timing = new Timing();

	public void SubmitTruck(String authToken, int truckid) throws IOException {

		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/truck/submit/application/{truckId}";

		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				pathParam("truckId",truckid).
				contentType("application/json").
				body(json.toString()).
				when().log().all().
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
	
	public String getTruckStatus() {
		return jsonresponse.getString("data.status").toString();
	}
	
	public String getErrorCode() {
		return jsonresponse.getString("errorCode").toString();
	}

}
