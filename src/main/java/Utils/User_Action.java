package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class User_Action {

	public User_Action() {

	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void ApproveUser(String authToken, int roleid, String userid) throws IOException {

		testdata = new TestData();
		
		json.addProperty("userId", userid);
		json.addProperty("roleId", roleid);
		json.addProperty("comment", "Admin approved test user");
		json.addProperty("zipCode", new Randomgenerator().generateRandomNumber(4));
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/approve";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										contentType("application/json").
										body(json.toString()).log().all().
										when().
										post().
										asString());

		 jsonresponse.prettyPrint();

	}

	public void RejectUser(String authToken, int roleid, String userid) throws IOException {

		testdata = new TestData();
		
		json.addProperty("userId", userid);
		json.addProperty("roleId", roleid);
		json.addProperty("reason", "Admin rejected test user");
		
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/reject";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										contentType("application/json").
										body(json.toString()).log().all().
										when().
										post().
										asString());

		 jsonresponse.prettyPrint();

	}

	public void ActivateUser(String authToken, String userid) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/activate/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("deact", false).
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		// jsonresponse.prettyPrint();

	}

	public void DeActivateUser(String authToken, String userid) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/activate/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("deact", true).
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}
	
	
	public void ApproveEnterpriseUser(String authToken, String userid) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/approveEnterprise/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										queryParam("reject", false).
										pathParam("userId", userid).
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		// jsonresponse.prettyPrint();

	}
	
	
	public void RejectEnterpriseUser(String authToken, String userid) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/approveEnterprise/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										queryParam("reject", true).
										pathParam("userId", userid).
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		// jsonresponse.prettyPrint();

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

}
