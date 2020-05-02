package Utils;

import java.io.IOException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class Trip_Action {

	public Trip_Action() {

	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void ApproveTrip(String authToken, int tripid) throws IOException {

		testdata = new TestData();

		json.addProperty("truckId", tripid);

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trip/{tripId}/approve";

		jsonresponse = new JsonPath(RestAssured.
						 given().
						 header("authtoken", authToken).
						 pathParam("tripId", tripid).
						 contentType("application/json").
						 body(json.toString()).
						 when().
						 put().
						 asString());

		jsonresponse.prettyPrint();

	}

	public void RejectTrip(String authToken, int tripid) throws IOException {

		testdata = new TestData();

		json.addProperty("truckId", tripid);

		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/admin/trip/{tripId}/reject";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken).pathParam("tripId", tripid)
				.contentType("application/json").body(json.toString()).when().put().asString());

		jsonresponse.prettyPrint();

	}

}
