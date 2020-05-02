package Utils;

import java.io.IOException;
import java.time.Instant;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import Utils.TestData;

public class FODashboard {

	public FODashboard() {

	}

	TestData testdata;
	JsonObject json = new JsonObject();
	JsonPath jsonresponse;

	public void FODashboardAPI_TruckListWithAssociatedTripStatus(String authToken, int page, int size)
			throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/truckstatus";

		jsonresponse = new JsonPath(
				RestAssured.given().header("authtoken", authToken).queryParam("page", page).queryParam("size", size)
						.contentType("application/json").body(json.toString()).when().get().asString());

		jsonresponse.prettyPrint();

	}

	public void FODashboardAPI_CompletedTripList(String authToken, int page, int size) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/trips";

		jsonresponse = new JsonPath(
				RestAssured.given().header("authtoken", authToken).queryParam("page", page).queryParam("size", size)
						.contentType("application/json").body(json.toString()).when().get().asString());

		jsonresponse.prettyPrint();

	}

	public void FODashboardAPI_FilterByTruckRegistrationNumber(String authToken, int page, int size,
			String truckRegistration) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/trips";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken).queryParam("page", page)
				.queryParam("size", size).queryParam("truckRegistration", truckRegistration)
				.contentType("application/json").body(json.toString()).when().get().asString());

		jsonresponse.prettyPrint();

	}

	public void FODashboardAPI_FilterByFromPickupDate(String authToken, int page, int size, String fromPickupDate)
			throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/trips";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken).queryParam("page", page)
				.queryParam("size", size).queryParam("fromPickupDate", fromPickupDate).contentType("application/json")
				.body(json.toString()).when().get().asString());

		jsonresponse.prettyPrint();

	}

	public void FODashboardAPI_FilterByToPickupDate(String authToken, int page, int size, String toPickupDate)
			throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/trips";

		jsonresponse = new JsonPath(RestAssured.given().header("authtoken", authToken).queryParam("page", page)
				.queryParam("size", size).queryParam("toPickupDate", toPickupDate).contentType("application/json")
				.body(json.toString()).when().get().asString());

		jsonresponse.prettyPrint();

	}

	public void FODashboardAPI_FilterByFromToPickupDates(String authToken, int page, int size, String fromPickupDate,
			String toPickupDate) throws IOException {

		testdata = new TestData();
		RestAssured.baseURI = testdata.properties.getProperty("env");
		RestAssured.basePath = "/api/v1/fleetOwner/dashboard/trips";

		jsonresponse = new JsonPath(
				RestAssured.given().header("authtoken", authToken).queryParam("page", page).queryParam("size", size)
						.queryParam("fromPickupDate", fromPickupDate).queryParam("toPickupDate", toPickupDate)
						.contentType("application/json").body(json.toString()).when().get().asString());

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

	public int getNumberofResults() {
		return Integer.parseInt(jsonresponse.getString("data.numberOfResults"));
	}

	public int getTripID(int value) {
		return Integer.parseInt(jsonresponse.getString("data.truckStatusDetail.contentList[" + value + "].tripId"));
	}

	public String getTruckStatus(int value) {
		return jsonresponse.getString("data.truckStatusDetail.contentList[" + value + "].tripStatus");
	}

	public int getTotalOnDutyTruck() {
		return Integer.parseInt(jsonresponse.getString("data.totalOnDutyTruck"));
	}

	public int getTotalOffDutyTruck() {
		return Integer.parseInt(jsonresponse.getString("data.totalOffDutyTruck"));
	}

	public int getTotalTruck() {
		return Integer.parseInt(jsonresponse.getString("data.totalTruck"));
	}
	
	public int getTotalTripsTaken() {
		return Integer.parseInt(jsonresponse.getString("data.totalTripTaken"));
	}
	
	public int getTotalAmountPaid() {
		return Integer.parseInt(jsonresponse.getString("data.totalAmountPaid"));
	}
	
	
	

}
