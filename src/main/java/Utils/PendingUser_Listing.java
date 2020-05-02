package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class PendingUser_Listing {
	
	public PendingUser_Listing() {
		
	}
	
	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void Pending_Customers_List(String authToken, int page, int size) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/pending/customers";

		jsonresponse = new JsonPath(
				RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("size", size).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());

		jsonresponse.prettyPrint();

	}

	public void Pending_FleetOwners_List(String authToken, int page, int size) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/pending/fleetOwners";

		jsonresponse = new JsonPath(
				RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("size", size).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());

		jsonresponse.prettyPrint();

	}

	public void Pending_Drivers_List(String authToken, int page, int size) throws IOException {
		
		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");;
		RestAssured.basePath = "/api/v1/admin/pending/drivers";

		jsonresponse = new JsonPath(
				RestAssured.
				given().
				header("authtoken", authToken).
				queryParam("page", page).
				queryParam("size", size).
				contentType("application/json").
				body(json.toString()).
				when().
				get().
				asString());

		jsonresponse.prettyPrint();

	}

	public String getUserID() {
		return jsonresponse.getString("data.contentList[0].id");
	}
	
	public String getUserMobileno() {
		return jsonresponse.getString("data.contentList[0].mobileNum");
	}
	
	public String getUserReferralCode() {
		return jsonresponse.getString("data.contentList[0].referrelCode");
	}

}
