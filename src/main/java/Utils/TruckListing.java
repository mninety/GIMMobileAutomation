package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class TruckListing {

	public TruckListing() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void PendingTruckslist(String authToken, String partnerMobileNumber, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trucklist";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										queryParam("partnerMobileNumber", partnerMobileNumber).
										queryParam("truckStatus", "PENDING").
										queryParam("page", page).
										queryParam("size", size).
										queryParam("status", "PENDING").
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}

	public void ApprovedTruckslist(String authToken, int userid, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trucks/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("page", page).
										queryParam("size", size).
										queryParam("status", "APPROVED").
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}

	
	public void DraftedTruckslist(String authToken, int userid, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trucks/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("page", page).
										queryParam("size", size).
										queryParam("status", "DRAFTED").
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}

	
	public void RejectedTruckslist(String authToken, int userid, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trucks/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("page", page).
										queryParam("size", size).
										queryParam("status", "REJECTED").
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}

	
	public void ExpiredTruckslist(String authToken, int userid, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trucks/{userId}";

		jsonresponse = new JsonPath(RestAssured.
										given().
										header("authtoken", authToken).
										pathParam("userId", userid).
										queryParam("page", page).
										queryParam("size", size).
										queryParam("status", "EXPIRED").
										contentType("application/json").
										body(json.toString()).
										when().
										get().
										asString());

		 jsonresponse.prettyPrint();

	}
	
	public String getTruckID() {
		return  jsonresponse.getString("data.contentList[0].id");
	}

	
}
