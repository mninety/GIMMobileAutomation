package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class DriverAssociationRequest {
	
public DriverAssociationRequest() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void Accept(String authToken, int driveruserid) throws IOException {
		
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/driver/update/driverInfo";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("reject", false).
				pathParam("driverUserId", driveruserid).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());
		
		//jsonresponse.prettyPrint();
			
	}
	
	public void Reject(String authToken, int driveruserid) throws IOException {
		
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/driver/update/driverInfo";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("reject", true).
				pathParam("driverUserId", driveruserid).
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
	
	public String getStatusValue() {
		return jsonresponse.getString("data.status").toString();
	}

	

}
