package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;


public class GetOTP {
	
	public GetOTP() {
		
	}
	TestData testdata;
	Randomgenerator rnd = new Randomgenerator();
	
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;
	
	public void OTPAPI(String mobno) throws IOException {
		testdata = new TestData();
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/auth/getOtp";
		
		jsonresponse = new JsonPath(RestAssured.
				given().
				queryParam("mobileNumber", mobno).
				queryParam("secret","123456").
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
	
	public String getMobileNumber() {
		return jsonresponse.getString("data.mobileNumber").toString();
	}
	
	public String getOTP() {
		return jsonresponse.getString("data.otp").toString();
	}
	
	public String getID() {
		return jsonresponse.getString("data.id").toString();
	}
	
	public String getOTPStatus() {
		return jsonresponse.getString("data.otpStatus").toString();
	}
	
	public String  getOTPExpiryDate() {
		return jsonresponse.getString("data.otpExpiryDate").toString();
	}
	
	public String getSentStatus() {
		return jsonresponse.getString("data.sentStatus").toString();
	}
	
	
	
	
	
	
	 
	
	
	
	

}
